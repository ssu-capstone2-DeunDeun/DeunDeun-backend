package kr.co.deundeun.groopy.domain.clubApply;

import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.comment.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubApply extends BaseEntity {

  private Long clubRecruitId;

  private ClubApplyStatus clubApplyStatus;

  @OneToMany(mappedBy = "clubApply")
  @OrderBy
  private List<ClubApplyAnswer> clubApplyAnswers;

  @OneToMany(mappedBy = "clubApply")
  private List<Comment> comments;

}
