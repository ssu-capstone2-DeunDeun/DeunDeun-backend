package kr.co.deundeun.groopy.controller.clubRecruit.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecruitSummaryResponseDto {

    private Long id;

    private String title;

    private int generation;

    private LocalDateTime modifiedAt;

    public static List<RecruitSummaryResponseDto> listOf(List<ClubRecruit> recruits){
        return recruits.stream()
                .map(RecruitSummaryResponseDto::of)
                .collect(Collectors.toList());
    }

    public static RecruitSummaryResponseDto of(ClubRecruit clubRecruit){
        return new RecruitSummaryResponseDto(clubRecruit.getId(), clubRecruit.getTitle(), clubRecruit.getGeneration(), clubRecruit.getModifiedAt());
    }

    public RecruitSummaryResponseDto(Long id, String title, int generation, LocalDateTime modifiedAt){
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.modifiedAt = modifiedAt;
    }

}
