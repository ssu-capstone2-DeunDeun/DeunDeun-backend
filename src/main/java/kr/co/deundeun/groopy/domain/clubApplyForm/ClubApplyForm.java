package kr.co.deundeun.groopy.domain.clubApplyForm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ClubApplyForm extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  @OneToMany(mappedBy = "clubApplyForm", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy
  private List<ClubRecruitQuestion> clubRecruitQuestions = new ArrayList<>();

  private String title;

  @Builder
  public ClubApplyForm(Club club, String title, List<ClubRecruitQuestion> clubRecruitQuestions) {
    initClub(club);
    this.title = title;
    clubRecruitQuestions.forEach(clubRecruitQuestion -> clubRecruitQuestion.initClubApplyForm(this));
  }

  private void initClub(Club club){
    this.club = club;
    club.getClubApplyForms().add(this);
  }
}
