package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.config.AppProperties;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.message.ContentType;
import kr.co.deundeun.groopy.dto.message.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Transactional
@Service
public class MailService {

  private final JavaMailSender javaMailSender;

  private final AppProperties appProperties;

  private static final String admin = "groopyofficial@gmail.com";

  public void sendRegisterMail(Club club, User user) {
    String text = buildBody(ContentType.REGISTER, club, user);
    send(ContentType.REGISTER, club, admin, text);
  }

  public void sendMail(MessageRequest messageRequest, Club club) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper =
          new MimeMessageHelper(message, true, "UTF-8");

      String[] emails = messageRequest.getEmails().toArray(new String[0]);
      ContentType contentType = messageRequest.getContentType();

      mimeMessageHelper.setFrom(admin, "groopy");
      mimeMessageHelper.setTo(emails);
      mimeMessageHelper.setSubject(buildSubject(contentType, club));
      mimeMessageHelper.setText(messageRequest.getMessage(), true);

      javaMailSender.send(message);
    } catch (MessagingException |
        UnsupportedEncodingException ignored) {
    }
  }

  public void send(ContentType contentType, Club club, String to, String text) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper =
          new MimeMessageHelper(message, true, "UTF-8");

      mimeMessageHelper.setFrom(admin, "groopy");
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setSubject(buildSubject(contentType, club));
      mimeMessageHelper.setText(text, true);

      javaMailSender.send(message);
    } catch (MessagingException |
        UnsupportedEncodingException ignored) {
    }
  }

  private String buildSubject(ContentType contentType, Club club) {
    String title;
    String clubName = club.getClubName();
    switch (contentType) {
      case REGISTER:
        title = "동아리 등록 신청 #";
        break;
      case INTERVIEW:
        title = "동아리 면접 알림 #";
        break;
      case RECRUIT:
        title = "동아리 모집 알림 #";
        break;
      case PASS:
        title = "동아리 합격 알림 #";
        break;
      default:
        title = "잘못된 이메일 전송 입니다.";
        break;
    }
    return title + clubName;
  }


  private String buildBody(ContentType contentType, Club club, User user) {
    String body = "";
    switch (contentType) {
      case REGISTER:
        body = buildRegisterClub(club, user);
        break;
    }
    return body.toString();
  }

  private String buildRegisterClub(Club club, User user) {
    StringBuilder body = new StringBuilder();
    body.append("<html> <body> ");
    body.append("<h1>동아리 등록 신청 정보</h1>");
    body.append(buildApplicantUser(user));
    body.append("<h2> 클럽 이름 </h2>");
    body.append("<p>")
        .append(club.getClubName())
        .append("</p>");
    body.append("<h3> 카테고리 </h3>");
    body.append("<p>")
        .append(club.getCategoryType())
        .append("</p>");
    body.append("<h3> 소개글 </h3>");
    body.append("<p>")
        .append(club.getIntroduction())
        .append("</p>");
    body.append("<h2> 승인링크 </h2>");
    body.append(appProperties.getServerUrl())
        .append("/clubs/");
    body.append(club.getId())
        .append("/approve");
    body.append("</body> </html>");
    return body.toString();
  }

  private String buildApplicantUser(User user) {
    StringBuilder applicantInfo = new StringBuilder();
    applicantInfo.append("<h2> 신청자 정보 </h2>");
    applicantInfo.append("<ul> ");
    applicantInfo.append("<li> 이름 : ")
                 .append(user.getName())
                 .append("</li>");
    applicantInfo.append("<li> 닉네임 : ")
                 .append(user.getNickname())
                 .append("</li>");
    applicantInfo.append("<li> 번호 : ")
                 .append(user.getPhoneNumber())
                 .append("</li>");
    applicantInfo.append("<li> 이메일 : ")
                 .append(user.getEmail())
                 .append("</li>");
    applicantInfo.append("</ul> ");
    return applicantInfo.toString();
  }

  private String buildApplicantClub(Club club) {
    StringBuilder applicantInfo = new StringBuilder();
    applicantInfo.append("<h2> 동아리 정보 </h2>");
    applicantInfo.append("<ul> ");
    applicantInfo.append("<li> 동아리 이름 : ")
                 .append(club.getClubName())
                 .append("</li>");
    applicantInfo.append("<li> 기수 : ")
                 .append(club.getGeneration())
                 .append("</li>");
    applicantInfo.append("</ul> ");
    return applicantInfo.toString();
  }
}
