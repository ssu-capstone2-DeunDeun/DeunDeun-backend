package kr.co.deundeun.groopy.domain.comment;

import java.util.ArrayList;
import java.util.List;

import kr.co.deundeun.groopy.dto.comment.CommentRequestDto;
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
  private List<Comment> childComments = new ArrayList<>();

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
    initParentComment(parentComment);
    initClubRecruit(clubRecruit);
    initClubApply(clubApply);
    initPost(post);
  }

  private void initParentComment(Comment parentComment) {
    if (parentComment == null) {
      this.parentComment = null;
      return;
    }
    this.parentComment = parentComment;
    parentComment.getChildComments().add(this);
  }

  private void initClubApply(ClubApply clubApply){
    this.clubApply = clubApply;
    clubApply.getComments().add(this);
  }

  private void initClubRecruit(ClubRecruit clubRecruit){
    this.clubRecruit = clubRecruit;
    clubRecruit.getComments().add(this);
  }

  private void initPost(Post post){
    this.post = post;
    post.getComments().add(this);
  }

  public void updateComment(CommentRequestDto commentRequestDto){
    this.comment = commentRequestDto.getComment();
  }

  public void deleteRelationship(){
    deleteParentComment();
    switch (this.commentType){
      case APPLY_COMMENT:
        this.clubApply.decreaseCommentCount();
        deleteClubApply();
        break;
      case  RECRUIT_COMMENT:
        this.clubRecruit.decreaseCommentCount();
        deleteClubRecruit();
        break;
      case POST_COMMENT:
        this.post.decreaseCommentCount();
        deletePost();
        break;
    }
  }

  private void deleteParentComment(){
    if (this.parentComment == null){
      return;
    }
    this.parentComment.getChildComments().remove(this);
    this.parentComment = null;
  }

  private void deleteClubApply(){
    if(this.clubApply == null){
      return;
    }
    this.clubApply.getComments().remove(this);
    this.clubApply = null;
  }

  private void deleteClubRecruit(){
    if(this.clubRecruit == null){
      return;
    }
    this.clubRecruit.getComments().remove(this);
    this.clubRecruit = null;
  }

  private void deletePost(){
    if(this.post == null){
      return;
    }
    this.post.getComments().remove(this);
    this.post = null;
  }
}
