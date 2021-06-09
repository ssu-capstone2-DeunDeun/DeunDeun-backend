package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ApplyFormRequestDto {

  @NotBlank
  private String title;

  @NotEmpty
  private List<RecruitQuestionRequestDto> recruitQuestionRequestDtos;

  public ClubApplyForm toEntity(Club club) {
    List<ClubRecruitQuestion> clubRecruitQuestions = RecruitQuestionRequestDto.toEntityList(recruitQuestionRequestDtos);
    return ClubApplyForm.builder()
                        .club(club)
                        .title(title)
                        .clubRecruitQuestions(clubRecruitQuestions)
                        .build();
  }
}
