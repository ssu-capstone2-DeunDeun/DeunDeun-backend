package kr.co.deundeun.groopy.dto.comment;

import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class CommentResponseDto {

    private Long id;

    private CommentType commentType;

    private String comment;

    private String author;

    private Long parentCommentId;

    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.commentType = comment.getCommentType();
        this.comment = comment.getComment();
        this.author = comment.getUser().getNickname();
        this.parentCommentId = getParentCommentId(comment);
        this.createdAt = comment.getCreatedAt();
    }

    private Long getParentCommentId(Comment comment) {
        if (comment.getParentComment() == null) {
            return -1L;
        }
        return comment.getParentComment().getId();
    }

    public static List<CommentResponseDto> ofList(List<Comment> comments){
        return comments.stream()
                       .map(CommentResponseDto::new)
                       .collect(Collectors.toList());
    }
}
