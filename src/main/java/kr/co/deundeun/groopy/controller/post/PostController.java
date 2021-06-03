package kr.co.deundeun.groopy.controller.post;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.common.page.PageRequestDto;
import kr.co.deundeun.groopy.dto.post.PostRequestDto;
import kr.co.deundeun.groopy.dto.post.PostResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/clubs/{clubId}/posts")  // 특정 클럽, 게시글 생성
    public ResponseEntity<PostResponseDto> post(@Me User user, @PathVariable Long clubId, @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.post(user.getNickname(), clubId, postRequestDto));
    }

    @GetMapping("/posts/{postId}")  // 게시글 상세보기
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PatchMapping("/posts/{postId}") // 게시글 수정하기
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok().body(postService.updatePost(postId, postRequestDto));
    }

    @DeleteMapping("/posts/{postId}") // 게시글 삭제하기
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    @GetMapping("/clubs/{clubId}/posts") // 특정 클럽, 게시글 전체보기
    public ResponseEntity<Page<PostResponseDto>> getClubPosts(@PathVariable Long clubId, final PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(postService.getClubPosts(clubId, pageRequestDto));
    }

    @GetMapping("/posts")  // 모든 클럽의 게시글 전체보기
    public ResponseEntity<Page<PostResponseDto>> getPosts(final PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(postService.getPosts(pageRequestDto));
    }

}
