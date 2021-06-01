package kr.co.deundeun.groopy.dto.message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Message {
    private String to;
    private String content;

    public Message(String to, String content){
        this.to = to;
        this.content = content;
    }

    public static List<Message> ofList(List<String> phoneNumbers, String message){
        return phoneNumbers.stream()
                .map(phoneNumber -> new Message(phoneNumber, message))
                .collect(Collectors.toList());
    }
}
