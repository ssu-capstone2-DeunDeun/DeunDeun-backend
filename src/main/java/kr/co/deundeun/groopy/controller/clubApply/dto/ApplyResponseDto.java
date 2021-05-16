package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplyResponseDto {

    private Long applyId;

    private List<ApplyAnswerDto> applyAnswers;

    public static ApplyResponseDto of(ClubApply clubApply){
        return new ApplyResponseDto(clubApply.getId(), ApplyAnswerDto.ofList(clubApply.getClubApplyAnswers()));
    }
}
