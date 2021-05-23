package kr.co.deundeun.groopy.domain.clubRecruit;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.controller.clubRecruit.dto.RecruitRequestDto;
import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.comment.Comment;
import kr.co.deundeun.groopy.domain.image.ClubImage;
import kr.co.deundeun.groopy.domain.image.ClubRecruitImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruit extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private int generation;

    private String title;

    private String content;

    private LocalDateTime submitStartDate;

    private LocalDateTime submitEndDate;

    private LocalDateTime documentPassStartDate;

    private LocalDateTime documentPassEndDate;

    private LocalDateTime interviewStartDate;

    private LocalDateTime interviewEndDate;

    private LocalDateTime finalPassStartDate;

    private LocalDateTime finalPassEndDate;

    private int likeCount;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitImage> clubRecruitImages;

    @OneToMany(mappedBy = "clubRecruit")
    private List<Comment> comments;

    @OneToMany(mappedBy = "clubRecruit")
    private List<ClubRecruitQuestion> clubRecruitQuestions;

    @Builder
    public ClubRecruit(Club club, int generation, List<ClubRecruitImage> clubRecruitImages,
                       String title, String content,
                       LocalDateTime submitStartDate, LocalDateTime submitEndDate,
                       LocalDateTime documentPassStartDate, LocalDateTime documentPassEndDate,
                       LocalDateTime interviewStartDate, LocalDateTime interviewEndDate,
                       LocalDateTime finalPassStartDate, LocalDateTime finalPassEndDate,
                       List<Comment> comments, List<ClubRecruitQuestion> clubRecruitQuestions){
        this.club = club;
        this.generation = generation;
        this.content = content;
        this.title = title;
        this.submitStartDate = submitStartDate;
        this.submitEndDate = submitEndDate;
        this.documentPassStartDate = documentPassStartDate;
        this.documentPassEndDate = documentPassEndDate;
        this.interviewStartDate = interviewStartDate;
        this.interviewEndDate = interviewEndDate;
        this.finalPassStartDate = finalPassStartDate;
        this.finalPassEndDate = finalPassEndDate;
        this.clubRecruitImages = clubRecruitImages;
        this.comments = comments;
        this.clubRecruitQuestions = clubRecruitQuestions;
    }

    public void update(RecruitRequestDto recruitRequestDto){
        this.generation = recruitRequestDto.getGeneration();
        this.content = recruitRequestDto.getContent();
        this.title = recruitRequestDto.getTitle();
        this.submitStartDate = recruitRequestDto.getSubmitStartDate();
        this.submitEndDate = recruitRequestDto.getSubmitEndDate();
        this.documentPassStartDate = recruitRequestDto.getDocumentPassStartDate();
        this.documentPassEndDate = recruitRequestDto.getDocumentPassEndDate();
        this.interviewStartDate = recruitRequestDto.getInterviewStartDate();
        this.interviewEndDate = recruitRequestDto.getInterviewEndDate();
        this.finalPassStartDate = recruitRequestDto.getFinalPassStartDate();
        this.finalPassEndDate = recruitRequestDto.getFinalPassEndDate();
    }

    public void setClubRecruitImage(ClubRecruitImage clubRecruitImage){
        if(clubRecruitImages.stream().anyMatch(image -> image.toImageUrl()
                .equals(clubRecruitImage.getImageUrl()))) return;
        this.clubRecruitImages.add(clubRecruitImage);
        clubRecruitImage.setClubRecruit(this);
    }

    public void setClubRecruitImages(List<ClubRecruitImage> clubRecruitImages){
        if(this.clubRecruitImages == null) this.clubRecruitImages = new ArrayList<>();
        clubRecruitImages.forEach(this::setClubRecruitImage);
    }

}
