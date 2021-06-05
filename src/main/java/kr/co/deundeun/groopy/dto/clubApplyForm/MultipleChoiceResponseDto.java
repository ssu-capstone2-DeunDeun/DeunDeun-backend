package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.MultipleChoice;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MultipleChoiceResponseDto {
  private int choiceNumber;
  private String choiceContent;

  public MultipleChoiceResponseDto(MultipleChoice multipleChoice) {
    this.choiceNumber = multipleChoice.getChoiceNumber();
    this.choiceContent = multipleChoice.getChoiceContent();
  }

  public static List<MultipleChoiceResponseDto> listOf(List<MultipleChoice> multipleChoices){
    return multipleChoices.stream()
                          .map(MultipleChoiceResponseDto::new)
                          .collect(
                              Collectors.toList());
  }
}
