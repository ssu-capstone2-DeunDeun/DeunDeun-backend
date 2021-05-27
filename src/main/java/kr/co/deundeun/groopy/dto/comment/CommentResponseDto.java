package kr.co.deundeun.groopy.dto.comment;

import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentResponseDto {

    private Long id;

    private CommentType commentType;

    private String comment;

    private String author;

    private Long parentCommentId;

    private LocalDateTime createdAt;

    public static List<CommentResponseDto> ofList(List<Comment> comments){
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.commentType = comment.getCommentType();
        this.comment = comment.getComment();
        this.author = comment.getUser().getNickname();
        if(comment.getParentComment() != null)
        this.parentCommentId = comment.getParentComment().getId();
        this.createdAt = comment.getCreatedAt();
    }
}
