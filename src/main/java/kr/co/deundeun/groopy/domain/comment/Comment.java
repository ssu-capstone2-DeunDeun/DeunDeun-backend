package kr.co.deundeun.groopy.domain.comment;

import java.util.List;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.comment.constant.CommentType;
import kr.co.deundeun.groopy.domain.post.Post;
import kr.co.deundeun.groopy.domain.user.User;
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




}
