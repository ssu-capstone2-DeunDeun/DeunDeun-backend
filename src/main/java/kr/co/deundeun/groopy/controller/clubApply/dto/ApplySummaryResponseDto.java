package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
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

    private String title;

    private int generation;

    private LocalDateTime modifiedAt;

    public static List<ApplySummaryResponseDto> listOf(List<ClubApply> clubApplies, List<ClubRecruit> clubRecruits){
        Map<Long, ClubRecruit> applyInfos = IntStream.range(0, clubApplies.size())
                .boxed().collect(Collectors.toMap(i -> clubApplies.get(i).getId(), clubRecruits::get));

        return applyInfos.entrySet().stream()
                .map(entry -> of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static ApplySummaryResponseDto of(Long id, ClubRecruit clubRecruit){
        return new ApplySummaryResponseDto(id, clubRecruit.getId(), clubRecruit.getTitle(), clubRecruit.getGeneration(), clubRecruit.getModifiedAt());
    }

    public ApplySummaryResponseDto(Long id, Long clubRecruitId, String title, int generation, LocalDateTime modifiedAt){
        this.clubApplyId = id;
        this.clubRecruitId = clubRecruitId;
        this.title = title;
        this.generation = generation;
        this.modifiedAt = modifiedAt;
    }

}
