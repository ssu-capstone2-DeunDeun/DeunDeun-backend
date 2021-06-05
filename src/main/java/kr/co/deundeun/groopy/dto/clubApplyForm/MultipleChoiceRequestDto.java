package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.MultipleChoice;
import lombok.Getter;

@Getter
public class MultipleChoiceRequestDto {

  private int choiceNumber;
  private String choiceContent;

  public MultipleChoice toEntity(){
    return MultipleChoice.builder()
                         .choiceNumber(choiceNumber)
                         .choiceContent(choiceContent)
                         .build();
  }

  public static List<MultipleChoice> toEntityList(List<MultipleChoiceRequestDto> multipleChoiceRequestDtos){
    return multipleChoiceRequestDtos.stream()
                                    .map(MultipleChoiceRequestDto::toEntity)
                                    .collect(Collectors.toList());
  }
}
