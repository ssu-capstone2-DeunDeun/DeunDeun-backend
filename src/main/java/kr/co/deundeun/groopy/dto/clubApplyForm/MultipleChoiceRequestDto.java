package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubApplyForm.MultipleChoice;
import lombok.Getter;

@Getter
public class MultipleChoiceRequestDto {

  private int choiceNumber;
  private String choiceContent;

  public MultipleChoice toMultipleChoice(ClubRecruitQuestion clubRecruitQuestion){
    return new MultipleChoice(clubRecruitQuestion, this);
  }

  public static List<MultipleChoice> ofList(ClubRecruitQuestion clubRecruitQuestion, List<MultipleChoiceRequestDto> multipleChoiceRequestDtos){
    return multipleChoiceRequestDtos.stream()
                                    .map(multipleChoiceRequestDto -> multipleChoiceRequestDto.toMultipleChoice(clubRecruitQuestion))
                                    .collect(Collectors.toList());
  }
}
