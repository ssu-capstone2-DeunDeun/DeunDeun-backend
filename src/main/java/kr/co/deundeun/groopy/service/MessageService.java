package kr.co.deundeun.groopy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.deundeun.groopy.config.SmsProperties;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.dto.message.*;
import kr.co.deundeun.groopy.exception.BadRequestException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Transactional
@Service
public class MessageService {

  private final String serviceId;
  private final String secretKey;
  private final String accessKey;
  private final String from;
  private final String SEND_API_URL;
  private static final String COUNTRY_CODE = "82";
  private static final String CONTENT = "GROOPY";
  private static final String HOST = "https://sens.apigw.ntruss.com";
  private static final String URL = "/sms/v2/services/";
  private static final String MESSAGE_API = "/messages";

  public MessageService(SmsProperties smsProperties) {
    this.serviceId = smsProperties.getServiceId();
    this.accessKey = smsProperties.getAccessKey();
    this.secretKey = smsProperties.getSecretKey();
    this.from = smsProperties.getFrom();
    this.SEND_API_URL = HOST + URL + serviceId + MESSAGE_API;
  }

  public String sendMessage(MessageRequest messageRequest, Club club) {
    if (messageRequest.getMessage().isEmpty()) {
      return "";
    }

    String timestamp = Long.toString(System.currentTimeMillis());

    HttpHeaders headers = getHeaders(timestamp);
    String body = getSendMessageBody(messageRequest, club.getClubName());

    HttpEntity<String> request = new HttpEntity<>(body, headers); // body + header 요청

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responses = restTemplate.postForEntity(SEND_API_URL, request,
                                                                  String.class);

    return responses.toString();
  }

  private HttpHeaders getHeaders(String timestamp) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("x-ncp-apigw-timestamp", timestamp); // 현재 시간 설정 header
    headers.set("x-ncp-iam-access-key", accessKey); // accessKey header
    headers.set("x-ncp-apigw-signature-v2", makeSignature(timestamp)); // 시간 기준 secretKey로 인증
    return headers;
  }

  public String getType(String message) {
    if (message.getBytes(StandardCharsets.UTF_8).length < 80) {
      return MessageType.SMS.toString();
    } else {
      return MessageType.LMS.toString();
    }
  }

  private String getSendMessageBody(MessageRequest messageRequest, String clubName) {
    List<Message> messages = Message.ofList(messageRequest.getPhoneNumbers(),
                                            messageRequest.getMessage());
    String messageType = getType(messageRequest.getMessage());

    SmsRequestDto smsRequestDto =
        new SmsRequestDto(messageType, COUNTRY_CODE, from, CONTENT, messages);
    ObjectMapper objectMapper = new ObjectMapper();

    String body = null;
    try {
      body = objectMapper.writeValueAsString(smsRequestDto); // body 생성
    } catch (JsonProcessingException ex) {
      throw new BadRequestException("메시지 전송 정보가 잘못되었습니다.");
    }
    return body;
  }

  public String makeSignature(String timestamp) {
    String url = "/sms/v2/services/" + serviceId + "/messages";

    String space = " ";
    String newLine = "\n";
    String method = "POST";
    String message = new StringBuilder()
        .append(method)
        .append(space)
        .append(url)
        .append(newLine)
        .append(timestamp)
        .append(newLine)
        .append(accessKey)
        .toString();

    String encodeBase64String = "";
    try {
      SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(signingKey);
      byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
      encodeBase64String = Base64.encodeBase64String(rawHmac);
    } catch (Exception e) {
    }
    return encodeBase64String;
  }

}
