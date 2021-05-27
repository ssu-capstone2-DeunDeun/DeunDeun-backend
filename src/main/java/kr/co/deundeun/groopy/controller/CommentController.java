package kr.co.deundeun.groopy.controller;

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
    public ResponseEntity<Void> CommentApply(@Me User user, @PathVariable Long applyId,
                                             @Valid @RequestBody CommentRequestDto commentRequestDto) {
        commentService.commentApply(user, applyId, commentRequestDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/applies/{applyId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long applyId) {
        return ResponseEntity.ok(commentService.getApplyComments(applyId));
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
}
