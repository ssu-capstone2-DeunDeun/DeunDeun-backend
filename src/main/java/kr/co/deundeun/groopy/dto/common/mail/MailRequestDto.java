package kr.co.deundeun.groopy.dto.common.mail;

import kr.co.deundeun.groopy.config.AppProperties;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.ToString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@ToString
public class MailRequestDto {

    private static final String to = "groopyofficial@gmail.com";
    private static final String from = "groopyofficial@gmail.com";

    public static void sendRegisterClub(JavaMailSender javaMailSender, AppProperties appProperties, User user, Club club) {
        String subject = "동아리 등록 신청 #" + club.getId();

        StringBuilder body = new StringBuilder();
        body.append("<html> <body><h1>동아리 등록 신청 정보</h1>");
        body.append("<h2> 신청자 </h2>");
        body.append("<ul> ");
        body.append("<li> 이름 : ").append(user.getName()).append("</li>");
        body.append("<li> 닉네임 : ").append(user.getNickname()).append("</li>");
        body.append("<li> 번호 : ").append(user.getPhoneNumber()).append("</li>");
        body.append("<li> 이메일 : ").append(user.getEmail()).append("</li>");
        body.append("</ul> ");
        body.append("<h2> 클럽 이름 </h2>");
        body.append("<p>").append(club.getClubName()).append("</p>");
        body.append("<h2> 소개글 </h2>");
        body.append("<p>").append(club.getIntroduction()).append("</p>");
        body.append("<h2> 승인링크 </h2>");
        body.append(appProperties.getServerUrl()).append("/clubs/").append(club.getId()).append("/approve");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");

            mimeMessageHelper.setFrom(from, "groopy");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body.toString(), true);

            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException ignored) {

        }
    }


}
