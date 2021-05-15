package kr.co.deundeun.groopy.controller.post;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.post.dto.PostRequestDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/{clubName}/post")
    public ResponseEntity<Void> post(@Me User user, @PathVariable String clubName, @RequestBody PostRequestDto postRequestDto){
        postService.post(user.getNickname(), clubName, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
