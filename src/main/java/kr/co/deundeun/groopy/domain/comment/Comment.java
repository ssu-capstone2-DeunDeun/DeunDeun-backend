package kr.co.deundeun.groopy.domain.comment;

import java.util.List;

import kr.co.deundeun.groopy.controller.comment.dto.CommentRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private CommentType commentType;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  private Comment parentComment;

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
  private List<Comment> childComment;

  @ManyToOne
  private ClubRecruit clubRecruit;

  @ManyToOne
  private ClubApply clubApply;

  @ManyToOne
  private Post post;

  @Builder
  public Comment(CommentType commentType, User user, String comment, Comment parentComment,
                 ClubRecruit clubRecruit, ClubApply clubApply, Post post){
    this.commentType = commentType;
    this.user = user;
    this.comment = comment;
    this.parentComment = parentComment;
    this.clubRecruit = clubRecruit;
    this.clubApply = clubApply;
    this.post = post;
  }

  public void update(CommentRequestDto commentRequestDto){
    this.comment = commentRequestDto.getComment();
  }
}
