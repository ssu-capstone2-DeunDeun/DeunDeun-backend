package kr.co.deundeun.groopy.domain.clubApplyForm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.dto.clubApplyForm.ApplyFormRequestDto;
import kr.co.deundeun.groopy.dto.clubApplyForm.RecruitQuestionRequestDto;
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

  @OneToMany(mappedBy = "clubApplyForm", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy
  private List<ClubRecruitQuestion> clubRecruitQuestions = new ArrayList<>();

  private String title;

  public ClubApplyForm(Club club, ApplyFormRequestDto applyFormRequestDto) {
    this();
    setClub(club);
    this.title = applyFormRequestDto.getTitle();
    this.clubRecruitQuestions = RecruitQuestionRequestDto.ofList(this, applyFormRequestDto.getRecruitQuestionRequestDtos());
  }

  public void setClub(Club club){
    if (this.club != null) {
      this.club.getClubApplyForms().remove(this);
    }
    this.club = club;
    club.getClubApplyForms().add(this);
  }
}
