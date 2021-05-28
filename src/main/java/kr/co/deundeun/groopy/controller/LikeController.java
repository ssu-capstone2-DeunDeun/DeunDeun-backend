package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.like.LikeResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.post.PostResponseDto;
import kr.co.deundeun.groopy.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/like")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/clubs/{clubId}")
    public ResponseEntity<Void> likeClub(@Me User user, @PathVariable Long clubId) {
        likeService.likeClub(user, clubId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clubs/{clubId}")
    public ResponseEntity<LikeResponseDto> getClubLike(@Me User user, @PathVariable Long clubId){
        return ResponseEntity.ok(likeService.getClubLike(user, clubId));
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<Void> likePost(@Me User user, @PathVariable Long postId) {
        likeService.likePost(user, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<LikeResponseDto> getPostLike(@Me User user, @PathVariable Long postId){
        return ResponseEntity.ok(likeService.getPostLike(user, postId));
    }

    @GetMapping("/clubs") // 내가 좋아요한 동아리 전체보기
    public ResponseEntity<List<ClubResponseDto>> getLikedClubs(@Me User user) {
        return ResponseEntity.ok(likeService.getLikedClubs(user));
    }

    @GetMapping("/posts") // 내가 좋아요한 게시글 전체보기
    public ResponseEntity<List<PostResponseDto>> getLikedPosts(@Me User user) {
        return ResponseEntity.ok(likeService.getLikedPosts(user));
    }

}
