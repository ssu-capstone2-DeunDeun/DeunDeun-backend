package kr.co.deundeun.groopy.controller.message;

import kr.co.deundeun.groopy.dto.message.MessageRequest;
import kr.co.deundeun.groopy.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody @Valid MessageRequest messageRequest){
        return ResponseEntity.ok(messageService.sendMessage(messageRequest));
    }

}
