package kr.co.deundeun.groopy.dto.clubRecruit;

import kr.co.deundeun.groopy.dao.ClubApplyFormRepository;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import kr.co.deundeun.groopy.helper.ClubApplyFormHelper;
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

    private Long clubApplyFormId;

    public ClubRecruit toClubRecruit(ClubApplyFormRepository clubApplyFormRepository, Club club) {
        ClubApplyForm clubApplyForm =
                ClubApplyFormHelper.findByClubApplyFormId(clubApplyFormRepository, clubApplyFormId);
        if(clubApplyForm == null)
            throw new IdNotFoundException("해당하는 clubApplyFormId가 없습니다.");

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
                .clubApplyForm(clubApplyForm)
                .build();
    }
}
