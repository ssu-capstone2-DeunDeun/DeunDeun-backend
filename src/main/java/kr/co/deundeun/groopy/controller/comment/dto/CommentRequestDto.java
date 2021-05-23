package kr.co.deundeun.groopy.controller.comment.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class CommentRequestDto {

    @NotEmpty
    private String comment;

    private CommentType commentType;

    private Long parentCommentId;

    public Comment toComment(User user, ClubApply clubApply) {
        return Comment.builder()
                .user(user)
                .commentType(commentType)
                .comment(comment)
                .clubApply(clubApply)
                .build();
    }

    public Comment toComment(User user, ClubApply clubApply, Comment parentComment) {
        return Comment.builder()
                .user(user)
                .commentType(commentType)
                .comment(comment)
                .clubApply(clubApply)
                .parentComment(parentComment)
                .build();
    }
}
