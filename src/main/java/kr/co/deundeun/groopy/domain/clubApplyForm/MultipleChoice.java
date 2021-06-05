package kr.co.deundeun.groopy.domain.clubApplyForm;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MultipleChoice extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  private ClubRecruitQuestion clubRecruitQuestion;

  private int choiceNumber; // 선지번호

  private String choiceContent; // 선지내용

  @Builder
  public MultipleChoice(int choiceNumber, String choiceContent) {
    this.choiceNumber = choiceNumber;
    this.choiceContent = choiceContent;
  }

  public void initClubRecruitQuestion(ClubRecruitQuestion clubRecruitQuestion){
    clubRecruitQuestion.getMultipleChoices().add(this);
    this.clubRecruitQuestion = clubRecruitQuestion;
  }
}
