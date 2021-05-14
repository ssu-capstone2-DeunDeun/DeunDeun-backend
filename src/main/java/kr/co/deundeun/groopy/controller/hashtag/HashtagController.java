package kr.co.deundeun.groopy.controller.hashtag;

import kr.co.deundeun.groopy.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping("/hashtags")
    public ResponseEntity<List<String>> getHashtags(){
        return ResponseEntity.ok(hashtagService.getHashtagNames());
    }
}
