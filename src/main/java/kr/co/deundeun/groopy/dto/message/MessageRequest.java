package kr.co.deundeun.groopy.dto.message;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MessageRequest {

    private Long senderId; // 보내는 사람 or 동아리

    private LocalDateTime reserveTime;

    private String message;

    private ContentType contentType;

    private List<String> phoneNumbers; // - 없이 붙여서 번호 전송

}
