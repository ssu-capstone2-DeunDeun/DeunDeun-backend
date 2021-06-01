package kr.co.deundeun.groopy.dto.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SmsRequestDto {
    private String type;
    private String countryCode;
    private String from;
    private String content;
    private List<Message> messages;

    public SmsRequestDto(String type, String countryCode, String from, String content, List<Message> messages) {
        this.type = type;
        this.countryCode = countryCode;
        this.from = from;
        this.content = content;
        this.messages = messages;
    }
}
