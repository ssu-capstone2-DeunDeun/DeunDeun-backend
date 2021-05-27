package kr.co.deundeun.groopy.domain.clubApplyForm;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import kr.co.deundeun.groopy.dto.clubApplyForm.MultipleChoiceRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
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

  public MultipleChoice(ClubRecruitQuestion clubRecruitQuestion, MultipleChoiceRequestDto multipleChoiceRequestDto) {
    this();
    setClubRecruitQuestion(clubRecruitQuestion);
    this.choiceNumber = multipleChoiceRequestDto.getChoiceNumber();
    this.choiceContent = multipleChoiceRequestDto.getChoiceContent();
  }

  public void setClubRecruitQuestion(ClubRecruitQuestion clubRecruitQuestion){
    if(this.clubRecruitQuestion != null){
      this.clubRecruitQuestion.getMultipleChoices().remove(this);
    }
    this.clubRecruitQuestion = clubRecruitQuestion;
    clubRecruitQuestion.getMultipleChoices().add(this);
  }
}
