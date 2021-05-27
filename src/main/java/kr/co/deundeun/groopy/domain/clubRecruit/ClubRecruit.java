package kr.co.deundeun.groopy.domain.clubRecruit;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import kr.co.deundeun.groopy.dto.clubRecruit.ClubRecruitRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.ClubRecruitStatus;
import kr.co.deundeun.groopy.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruit extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubApplyForm clubApplyForm;

    private int generation;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ClubRecruitStatus clubRecruitStatus;

    private LocalDateTime submitStartDate;

    private LocalDateTime submitEndDate;

    private LocalDateTime documentPassStartDate;

    private LocalDateTime documentPassEndDate;

    private LocalDateTime interviewStartDate;

    private LocalDateTime interviewEndDate;

    private LocalDateTime finalPassStartDate;

    private LocalDateTime finalPassEndDate;

    private int likeCount = 0;

    private int applicantCount = 0;

    @OneToMany(mappedBy = "clubRecruit")
    private List<Comment> comments;

    @Builder
    public ClubRecruit(Club club, int generation,
                       String title, String content,
                       LocalDateTime submitStartDate, LocalDateTime submitEndDate,
                       LocalDateTime documentPassStartDate, LocalDateTime documentPassEndDate,
                       LocalDateTime interviewStartDate, LocalDateTime interviewEndDate,
                       LocalDateTime finalPassStartDate, LocalDateTime finalPassEndDate,
                       List<Comment> comments){
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
        this.comments = comments;
    }

    public void update(ClubRecruitRequestDto clubRecruitRequestDto){
        this.generation = clubRecruitRequestDto.getGeneration();
        this.content = clubRecruitRequestDto.getContent();
        this.title = clubRecruitRequestDto.getTitle();
        this.submitStartDate = clubRecruitRequestDto.getSubmitStartDate();
        this.submitEndDate = clubRecruitRequestDto.getSubmitEndDate();
        this.documentPassStartDate = clubRecruitRequestDto.getDocumentPassStartDate();
        this.documentPassEndDate = clubRecruitRequestDto.getDocumentPassEndDate();
        this.interviewStartDate = clubRecruitRequestDto.getInterviewStartDate();
        this.interviewEndDate = clubRecruitRequestDto.getInterviewEndDate();
        this.finalPassStartDate = clubRecruitRequestDto.getFinalPassStartDate();
        this.finalPassEndDate = clubRecruitRequestDto.getFinalPassEndDate();
    }

    public void increaseApplicantCount(){
        this.applicantCount += 1;
    }

    public void decreaseApplicantCount(){
        if (this.applicantCount != 0) {
            this.applicantCount -= 1;
        }
    }
    public int getQuestionSize(){
        return this.getClubApplyForm().getClubRecruitQuestions().size();
    }

    public boolean hasApplicant(){
        return this.applicantCount != 0;
    }

    public List<ClubRecruitQuestion> getClubRecruitQuestions(){
        return clubApplyForm.getClubRecruitQuestions();
    }
}
