package kr.co.deundeun.groopy.domain.clubRecruit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ClubApplyForm extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  @OneToMany(mappedBy = "clubApplyForm")
  @OrderBy
  private List<ClubRecruitQuestion> clubRecruitQuestions = new ArrayList<>();

  private String title;

}
