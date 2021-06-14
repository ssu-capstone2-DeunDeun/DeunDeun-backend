package kr.co.deundeun.groopy.domain.clubRecruit;

import java.util.ArrayList;
import javax.persistence.CascadeType;
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

    private int recruitGeneration;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ClubRecruitStatus clubRecruitStatus;

    private LocalDateTime submitStartDate;

    private LocalDateTime submitEndDate;

    private LocalDateTime documentPassAnnounceDate;

    private LocalDateTime interviewStartDate;

    private LocalDateTime interviewEndDate;

    private LocalDateTime finalPassAnnounceDate;

    private int likeCount = 0;

    private int commentCount = 0;

    private int applicantCount = 0;

    private int viewCount = 0;

    @OneToMany(mappedBy = "clubRecruit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    private ClubRecruit(Club club,
                        String title, String content,
                        LocalDateTime submitStartDate, LocalDateTime submitEndDate,
                        LocalDateTime documentPassAnnounceDate,
                        LocalDateTime interviewStartDate, LocalDateTime interviewEndDate,
                        LocalDateTime finalPassAnnounceDate, ClubApplyForm clubApplyForm) {
        initClub(club);
        this.recruitGeneration = club.getGeneration() + 1;
        this.content = content;
        this.title = title;
        this.submitStartDate = submitStartDate;
        this.submitEndDate = submitEndDate;
        this.documentPassAnnounceDate = documentPassAnnounceDate;
        this.interviewStartDate = interviewStartDate;
        this.interviewEndDate = interviewEndDate;
        this.finalPassAnnounceDate = finalPassAnnounceDate;
        this.clubRecruitStatus = calculateClubRecruitStatus(submitStartDate, submitEndDate);
        this.clubApplyForm = clubApplyForm;
    }

    public ClubRecruitStatus calculateClubRecruitStatus(LocalDateTime submitStartDate, LocalDateTime submitEndDate) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(submitStartDate)) {
            return ClubRecruitStatus.WAITING;
        }
        if (now.isBefore(submitEndDate)) {
            return ClubRecruitStatus.RECRUIT;
        }
        return ClubRecruitStatus.END;
    }

    public void update(ClubRecruitRequestDto clubRecruitRequestDto) {
        this.recruitGeneration = clubRecruitRequestDto.getGeneration();
        this.content = clubRecruitRequestDto.getContent();
        this.title = clubRecruitRequestDto.getTitle();
        this.submitStartDate = clubRecruitRequestDto.getSubmitStartDate();
        this.submitEndDate = clubRecruitRequestDto.getSubmitEndDate();
        this.documentPassAnnounceDate = clubRecruitRequestDto.getDocumentPassAnnounceDate();
        this.interviewStartDate = clubRecruitRequestDto.getInterviewStartDate();
        this.interviewEndDate = clubRecruitRequestDto.getInterviewEndDate();
        this.finalPassAnnounceDate = clubRecruitRequestDto.getFinalPassAnnounceDate();
        this.clubRecruitStatus = calculateClubRecruitStatus(submitStartDate, submitEndDate);
    }

    public void initClub(Club club) {
        club.getClubRecruits().add(this);
        this.club = club;
    }

    public void increaseApplicantCount() {
        this.applicantCount += 1;
    }

    public void decreaseApplicantCount() {
        if (this.applicantCount <= 0) {
            return;
        }
        this.applicantCount -= 1;
    }

    public void increaseCommentCount() {
        this.commentCount += 1;
    }

    public void decreaseCommentCount() {
        if (this.commentCount <= 0) {
            return;
        }
        this.commentCount -= 1;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

    public int getQuestionSize() {
        return this.getClubApplyForm().getClubRecruitQuestions().size();
    }

    public boolean hasApplicant() {
        return this.applicantCount != 0;
    }

    public List<ClubRecruitQuestion> getClubRecruitQuestions() {
        return clubApplyForm.getClubRecruitQuestions();
    }

    public void deleteClubApplyForm() {
        this.clubApplyForm = null;
    }

    public void deleteClub() {
        this.club.getClubRecruits().remove(this);
        this.club = null;
    }

    public void updateClubRecruitStatus() {
        if (this.submitStartDate.isBefore(LocalDateTime.now()))
            startSubmit();

        if (this.submitEndDate.isBefore(LocalDateTime.now()))
            endSubmit();
    }

    public void startSubmit() {
        if (this.clubRecruitStatus.equals(ClubRecruitStatus.WAITING))
            this.clubRecruitStatus = ClubRecruitStatus.RECRUIT;
    }

    public void endSubmit() {
        if (this.clubRecruitStatus.equals(ClubRecruitStatus.RECRUIT))
            this.clubRecruitStatus = ClubRecruitStatus.END;
    }
}
