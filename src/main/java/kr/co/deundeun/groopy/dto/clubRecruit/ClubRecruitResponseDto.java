package kr.co.deundeun.groopy.dto.clubRecruit;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class ClubRecruitResponseDto {

    private Long id;

    private String title;

    private String content;

    private int generation;

    private ClubRecruitStatus clubRecruitStatus;

    private LocalDateTime submitStartDate;

    private LocalDateTime submitEndDate;

    private LocalDateTime documentPassAnnounceDate;

    private LocalDateTime interviewStartDate;

    private LocalDateTime interviewEndDate;

    private LocalDateTime finalPassAnnounceDate;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int likeCount = 0;

    private long remainDays = 0;

    private ClubRecruitResponseDto(ClubRecruit clubRecruit) {
        this.id = clubRecruit.getId();
        this.title = clubRecruit.getTitle();
        this.content = clubRecruit.getContent();
        this.generation = clubRecruit.getRecruitGeneration();
        this.submitStartDate = clubRecruit.getSubmitStartDate();
        this.submitEndDate = clubRecruit.getSubmitEndDate();
        this.documentPassAnnounceDate = clubRecruit.getDocumentPassAnnounceDate();
        this.interviewStartDate = clubRecruit.getInterviewStartDate();
        this.interviewEndDate = clubRecruit.getInterviewEndDate();
        this.finalPassAnnounceDate = clubRecruit.getFinalPassAnnounceDate();
        this.createdAt = clubRecruit.getCreatedAt();
        this.modifiedAt = clubRecruit.getModifiedAt();
        this.likeCount = clubRecruit.getLikeCount();
        this.clubRecruitStatus = clubRecruit.getClubRecruitStatus();
        this.remainDays = remainDays(submitEndDate);
    }

    public static ClubRecruitResponseDto of(ClubRecruit clubRecruit) {
        if (clubRecruit == null) return null;
        return new ClubRecruitResponseDto(clubRecruit);
    }

    public static List< ClubRecruitResponseDto > listOf(List< ClubRecruit > clubRecruits) {
        return clubRecruits.stream()
                .map(ClubRecruitResponseDto::new)
                .collect(Collectors.toList());
    }

    private long remainDays(LocalDateTime submitEndDate) {
        if (LocalDateTime.now().isBefore(submitEndDate)) {
            return ChronoUnit.DAYS.between(LocalDateTime.now(), submitEndDate);
        }
        return -1;
    }
}
