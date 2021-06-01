package kr.co.deundeun.groopy.controller.comment;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.comment.CommentRequestDto;
import kr.co.deundeun.groopy.dto.comment.CommentResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/applies/{applyId}/comments")
    public ResponseEntity<Void> commentClubApply(@Me User user, @PathVariable Long applyId,
                                                 @Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentClubApply(user, applyId, commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/applies/{applyId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getClubApplyComments(@PathVariable Long applyId) {
        return ResponseEntity.ok(commentService.getClubApplyComments(applyId));
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@Me User user, @PathVariable Long commentId,
                                              @Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(user, commentId, commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@Me User user, @PathVariable Long commentId) {
        commentService.deleteComment(user, commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> commentPost(@Me User user, @PathVariable Long postId,
                                             @Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentPost(user, postId, commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getPostComments(postId));
    }

    @PostMapping("/recruits/{recruitId}/comments")
    public ResponseEntity<Void> commentClubRecruit(@Me User user, @PathVariable Long recruitId,
                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentClubRecruit(user, recruitId, commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recruits/{recruitId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getClubRecruitComments(@PathVariable Long recruitId) {
        return ResponseEntity.ok(commentService.getClubRecruitComments(recruitId));
    }
}
