package kr.co.deundeun.groopy.controller.clubApplyForm.dto;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import lombok.Getter;

@Getter
public class ApplyFormResponseDto {

  private Long applyFormId;
  private String title;
  private List<RecruitQuestionResponseDto> recruitQuestionResponseDtos;

  public ApplyFormResponseDto(ClubApplyForm clubApplyForm) {
    this.applyFormId = clubApplyForm.getId();
    this.title = clubApplyForm.getTitle();
    this.recruitQuestionResponseDtos = RecruitQuestionResponseDto.listOf(clubApplyForm.getClubRecruitQuestions());
  }

  public static List<ApplyFormResponseDto> listOf(List<ClubApplyForm> clubApplyForms){
    return clubApplyForms.stream()
                         .map(ApplyFormResponseDto::new)
                         .collect(Collectors.toList());
  }
}
