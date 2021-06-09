package kr.co.deundeun.groopy.dto.comment;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ToString
@Getter
public class CommentRequestDto {

    @NotEmpty
    private String comment;

    private CommentType commentType;

    private Long parentCommentId;

    public Comment toClubApplyComment(User user, ClubApply clubApply, Comment parentComment) {
        clubApply.increaseCommentCount();
        return Comment.builder()
                      .commentType(commentType)
                      .user(user)
                      .comment(comment)
                      .clubApply(clubApply)
                      .parentComment(parentComment)
                      .build();
    }

    public Comment toPostComment(User user, Post post, Comment parentComment) {
        post.increaseCommentCount();
        return Comment.builder()
                      .commentType(commentType)
                      .user(user)
                      .comment(comment)
                      .post(post)
                      .parentComment(parentComment)
                      .build();
    }

    public Comment toClubRecruitComment(User user, ClubRecruit clubRecruit, Comment parentComment) {
        clubRecruit.increaseCommentCount();
        return Comment.builder()
                      .commentType(commentType)
                      .user(user)
                      .comment(comment)
                      .clubRecruit(clubRecruit)
                      .parentComment(parentComment)
                      .build();
    }
}
