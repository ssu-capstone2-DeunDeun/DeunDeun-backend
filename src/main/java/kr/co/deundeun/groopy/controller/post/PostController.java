package kr.co.deundeun.groopy.controller.post;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.common.page.PageRequestDto;
import kr.co.deundeun.groopy.controller.post.dto.PostRequestDto;
import kr.co.deundeun.groopy.controller.post.dto.PostResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/clubs/{clubName}/posts")
    public ResponseEntity<Void> post(@Me User user, @PathVariable String clubName, @RequestBody PostRequestDto postRequestDto) {
        postService.post(user.getNickname(), clubName, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clubs/{clubName}/posts")
    public ResponseEntity<Page<PostResponseDto>> getClubPosts(@PathVariable String clubName, final PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(postService.getClubPosts(clubName, pageRequestDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getPosts(final PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(postService.getPosts(pageRequestDto));
    }

    @GetMapping("/liked/posts")
    public ResponseEntity<List<PostResponseDto>> getLikedPosts(@Me User user) {
        return ResponseEntity.ok(postService.getLikedPosts(user));
    }
}
