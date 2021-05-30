package kr.co.deundeun.groopy.dto.clubRecruit;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ClubRecruitRequestDto {

    private String title;

    private String content;

    private int generation;

    @Future
    private LocalDateTime submitStartDate;

    @Future
    private LocalDateTime submitEndDate;

    @Future
    private LocalDateTime documentPassStartDate;

    @Future
    private LocalDateTime documentPassEndDate;

    @Future
    private LocalDateTime interviewStartDate;

    @Future
    private LocalDateTime interviewEndDate;

    @Future
    private LocalDateTime finalPassStartDate;

    @Future
    private LocalDateTime finalPassEndDate;

    public ClubRecruit toClubRecruit(Club club){
        return ClubRecruit.builder()
                .club(club)
                .title(title)
                .content(content)
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
