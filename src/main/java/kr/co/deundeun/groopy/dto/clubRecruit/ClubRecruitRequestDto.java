package kr.co.deundeun.groopy.dto.clubRecruit;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@ToString
@Getter
public class ClubRecruitRequestDto {

    private String title;

    private String content;

    private int generation;

    private LocalDateTime submitStartDate;

    @Future
    private LocalDateTime submitEndDate;

    @Future
    private LocalDateTime documentPassAnnounceDate;

    @Future
    private LocalDateTime interviewStartDate;

    @Future
    private LocalDateTime interviewEndDate;

    @Future
    private LocalDateTime finalPassAnnounceDate;

    public ClubRecruit toClubRecruit(Club club){

        return ClubRecruit.builder()
                          .club(club)
                          .title(title)
                          .content(content)
                          .submitStartDate(submitStartDate)
                          .submitEndDate(submitEndDate)
                          .documentPassAnnounceDate(documentPassAnnounceDate)
                          .interviewStartDate(interviewStartDate)
                          .interviewEndDate(interviewEndDate)
                          .finalPassAnnounceDate(finalPassAnnounceDate)
                          .build();
    }
}
