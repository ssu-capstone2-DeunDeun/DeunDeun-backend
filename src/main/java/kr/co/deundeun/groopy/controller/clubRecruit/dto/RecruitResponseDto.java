package kr.co.deundeun.groopy.controller.clubRecruit.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.image.ClubRecruitImage;
import kr.co.deundeun.groopy.domain.image.Image;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecruitResponseDto {

    private Long id;

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

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private List<String> recruitImageUrls;

    private int likeCount;

    private long remainDays;

    @Builder
    public RecruitResponseDto(ClubRecruit clubRecruit){
        if(clubRecruit == null) return;

        this.id = clubRecruit.getId();
        this.title = clubRecruit.getTitle();
        this.content = clubRecruit.getContent();
        this.generation = clubRecruit.getGeneration();
        this.submitStartDate = clubRecruit.getSubmitStartDate();
        this.submitEndDate = clubRecruit.getSubmitEndDate();
        this.documentPassStartDate = clubRecruit.getDocumentPassStartDate();
        this.documentPassEndDate = clubRecruit.getDocumentPassEndDate();
        this.interviewStartDate = clubRecruit.getInterviewStartDate();
        this.interviewEndDate = clubRecruit.getInterviewEndDate();
        this.finalPassStartDate = clubRecruit.getFinalPassStartDate();
        this.finalPassEndDate = clubRecruit.getFinalPassEndDate();
        this.createdAt = clubRecruit.getCreatedAt();
        this.modifiedAt = clubRecruit.getModifiedAt();
        this.likeCount = clubRecruit.getLikeCount();
        this.recruitImageUrls = toRecruitImageUrls(clubRecruit.getClubRecruitImages());
        this.remainDays = remainDays(submitEndDate);
    }

    public static RecruitResponseDto of(ClubRecruit clubRecruit){
        if(clubRecruit == null) return null;
        return RecruitResponseDto.builder()
                .clubRecruit(clubRecruit).build();
    }

    public static List<RecruitResponseDto> listOf(List<ClubRecruit> clubRecruits) {
        return clubRecruits.stream()
                .map(RecruitResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<String> toRecruitImageUrls(List<ClubRecruitImage> clubRecruitImages){
        return clubRecruitImages.stream()
                .map(Image::getImageUrl)
                .collect(Collectors.toList());
    }

    private long remainDays(LocalDateTime submitEndDate){
        long dayLeft = -1;

        if(LocalDateTime.now().isBefore(submitEndDate))
            dayLeft = ChronoUnit.DAYS.between(LocalDateTime.now(),submitEndDate);

        return dayLeft;
    }
}
