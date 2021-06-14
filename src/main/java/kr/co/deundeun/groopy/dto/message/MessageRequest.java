package kr.co.deundeun.groopy.dto.message;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
public class MessageRequest {

    private Long clubId; // 동아리

    private LocalDateTime reserveTime;

    @Size(max = 500)
    private String message;

    private ContentType contentType;

    private List<String> phoneNumbers; // - 없이 붙여서 번호 전송

    private List<String> emails;

}
