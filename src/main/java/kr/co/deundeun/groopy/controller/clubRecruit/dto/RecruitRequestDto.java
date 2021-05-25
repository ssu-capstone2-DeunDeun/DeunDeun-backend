package kr.co.deundeun.groopy.controller.clubRecruit.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RecruitRequestDto {

    private String title;

    private String content;

    private int generation;

    private LocalDateTime submitStartDate;

    private LocalDateTime submitEndDate;

    private LocalDateTime documentPassStartDate;

    private LocalDateTime documentPassEndDate;

    private LocalDateTime interviewStartDate;

    private LocalDateTime interviewEndDate;

    private LocalDateTime finalPassStartDate;

    private LocalDateTime finalPassEndDate;

    public ClubRecruit toClubRecruit(Club club){
        return ClubRecruit.builder()
                .club(club)
                .title(title)
                .content(content)
                .generation(generation)
                .submitStartDate(submitStartDate)
                .submitEndDate(submitEndDate)
                .documentPassStartDate(documentPassStartDate)
                .documentPassEndDate(documentPassEndDate)
                .interviewStartDate(interviewStartDate)
                .interviewEndDate(interviewEndDate)
                .finalPassStartDate(finalPassStartDate)
                .finalPassEndDate(finalPassEndDate)
                .build();
    }

}
