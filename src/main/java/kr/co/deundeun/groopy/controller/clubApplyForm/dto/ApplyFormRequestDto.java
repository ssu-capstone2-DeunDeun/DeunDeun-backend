package kr.co.deundeun.groopy.controller.clubApplyForm.dto;

import java.util.List;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import lombok.Getter;

@Getter
public class ApplyFormRequestDto {

  private String title;

  private List<RecruitQuestionRequestDto> recruitQuestionRequestDtos;

  public ClubApplyForm toClubApplyForm(Club club) {
    return new ClubApplyForm(club, this);
  }
}
