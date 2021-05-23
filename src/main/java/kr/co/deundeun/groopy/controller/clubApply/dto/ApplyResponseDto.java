package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplyResponseDto {

    private Long applyId;

    private Long recruitId;

    private ClubApplyStatus clubApplyStatus;

    private List<ApplyAnswerDto> applyAnswers;

    @Builder
    public ApplyResponseDto(ClubApply clubApply, ClubRecruit clubRecruit){
        this.applyId = clubApply.getId();
        this.recruitId = clubApply.getClubRecruitId();
        this.clubApplyStatus = clubApply.getClubApplyStatus();
        if(clubApply.getClubApplyAnswers() != null)
            this.applyAnswers = ApplyAnswerDto
                    .ofList(clubApply.getClubApplyAnswers(), clubRecruit);
    }

    public static ApplyResponseDto of(ClubApply clubApply, ClubRecruit clubRecruit) {
        return ApplyResponseDto.builder()
                .clubApply(clubApply)
                .clubRecruit(clubRecruit)
                .build();
    }

}
