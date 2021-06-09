package kr.co.deundeun.groopy.dto.clubApply;

import java.util.ArrayList;
import java.util.List;
import kr.co.deundeun.groopy.dto.clubApplyForm.RecruitQuestionResponseDto;
import kr.co.deundeun.groopy.domain.clubApply.ClubApplyAnswer;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ApplyQAResponseDto {

  private RecruitQuestionResponseDto recruitQuestionResponseDto;
  private String applyAnswer;

  @Builder
  public ApplyQAResponseDto(ClubRecruitQuestion clubRecruitQuestion, ClubApplyAnswer clubApplyAnswer) {
    this.recruitQuestionResponseDto = new RecruitQuestionResponseDto(clubRecruitQuestion);
    this.applyAnswer = clubApplyAnswer.getAnswer();
  }

  public static List<ApplyQAResponseDto> listOf(List<ClubRecruitQuestion> clubRecruitQuestions, List<ClubApplyAnswer> clubApplyAnswers) {
    List<ApplyQAResponseDto> applyQAResponseDtos = new ArrayList<>();
    for(int i=0; i < clubRecruitQuestions.size(); i++){
      ApplyQAResponseDto applyQAResponseDto = new ApplyQAResponseDto(clubRecruitQuestions.get(i), clubApplyAnswers.get(i));
      applyQAResponseDtos.add(applyQAResponseDto);
    }
    return applyQAResponseDtos;
  }
}
