package kr.co.deundeun.groopy.dto.clubApply;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ApplySummaryResponseDto {

    private Long clubRecruitId;
    private String title;
    private String clubName;
    private Long clubApplyId;
    private ClubApplyStatus clubApplyStatus;

    public ApplySummaryResponseDto(ClubApply clubApply) {

        ClubRecruit clubRecruit = clubApply.getClubRecruit();

        this.clubRecruitId = clubRecruit.getId();
        this.title = clubRecruit.getTitle();
        this.clubName = clubRecruit.getClub().getClubName();
        this.clubApplyId = clubApply.getId();
        this.clubApplyStatus = clubApply.getClubApplyStatus();
    }

    public static List<ApplySummaryResponseDto> listOf(List<ClubApply> clubApplies) {
        return clubApplies.stream()
                          .map(ApplySummaryResponseDto::new)
                          .collect(Collectors.toList());
//        Map<ClubApply, ClubRecruit> applyInfos = IntStream.range(0, clubApplies.size())
//                .boxed().collect(Collectors.toMap(clubApplies::get, clubRecruits::get));
//
//        return applyInfos.entrySet().stream()
//                .map(entry -> of(entry.getKey(), entry.getValue()))
//                .collect(Collectors.toList());
    }
}
