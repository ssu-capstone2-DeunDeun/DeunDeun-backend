package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class ApplySummaryResponseDto {

    private Long clubApplyId;

    private Long clubRecruitId;

    private String clubName;

    private String title;

    private int generation;

    private ClubApplyStatus clubApplyStatus;

    private LocalDateTime modifiedAt;

    public static List<ApplySummaryResponseDto> listOf(List<ClubApply> clubApplies, List<ClubRecruit> clubRecruits){
        Map<ClubApply, ClubRecruit> applyInfos = IntStream.range(0, clubApplies.size())
                .boxed().collect(Collectors.toMap(clubApplies::get, clubRecruits::get));

        return applyInfos.entrySet().stream()
                .map(entry -> of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static ApplySummaryResponseDto of(ClubApply clubApply, ClubRecruit clubRecruit){
        return new ApplySummaryResponseDto(clubApply, clubRecruit);
    }

    public ApplySummaryResponseDto(ClubApply clubApply, ClubRecruit clubRecruit){
        this.clubApplyId = clubApply.getId();
        this.clubRecruitId = clubRecruit.getId();
        this.clubName = clubRecruit.getClub().getClubName();
        this.title = clubRecruit.getTitle();
        this.generation = clubRecruit.getGeneration();
        this.clubApplyStatus = clubApply.getClubApplyStatus();
        this.modifiedAt = clubRecruit.getModifiedAt();
    }

}
