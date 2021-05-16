package kr.co.deundeun.groopy.controller.clubRecruit.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.Getter;

import java.time.LocalDateTime;

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

    private LocalDateTime modifiedAt;

    public RecruitResponseDto(ClubRecruit clubRecruit){
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
        this.modifiedAt = clubRecruit.getModifiedAt();
    }


}