package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplyResponseDto {

    private Long applyId;

    private Long recruitId;

    private List<ApplyAnswerDto> applyAnswers;

    @Builder
    public ApplyResponseDto(ClubApply clubApply){
        this.applyId = clubApply.getId();
        this.recruitId = clubApply.getClubRecruitId();
        if(clubApply.getClubApplyAnswers() != null)
            this.applyAnswers = ApplyAnswerDto
                    .ofList(clubApply.getClubApplyAnswers());
    }

    public static ApplyResponseDto of(ClubApply clubApply) {
        return ApplyResponseDto.builder()
                .clubApply(clubApply)
                .build();
    }
}
