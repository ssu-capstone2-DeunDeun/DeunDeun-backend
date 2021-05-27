package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.like.LikeResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/clubs/{clubId}/likes")
    public ResponseEntity<Void> likeClub(@Me User user, @PathVariable Long clubId) {
        likeService.likeClub(user, clubId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clubs/{clubId}/likes")
    public ResponseEntity<LikeResponseDto> getClubLike(@Me User user, @PathVariable Long clubId){
        return ResponseEntity.ok(likeService.getClubLike(user, clubId));
    }

    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<Void> likePost(@Me User user, @PathVariable Long postId) {
        likeService.likePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<LikeResponseDto> getPostLike(@Me User user, @PathVariable Long postId){
        return ResponseEntity.ok(likeService.getPostLike(user, postId));
    }


}
