package kr.co.deundeun.groopy.domain.clubApplyForm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class ClubRecruitQuestion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubApplyForm clubApplyForm;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String questionContent;

    private int questionNumber;

    /*
    주관식인 경우 : 빈 리스트 저장
    객관식인 경우 : 선택지 저장
     */
    @OneToMany(mappedBy = "clubRecruitQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("choiceNumber")
    private List< MultipleChoice > multipleChoices = new ArrayList<>();

    @Builder
    public ClubRecruitQuestion(QuestionType questionType, String questionContent, int questionNumber, List< MultipleChoice > multipleChoices) {
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.questionNumber = questionNumber;
        if (questionType.equals(QuestionType.MULTIPLE))
            multipleChoices.forEach(multipleChoice -> multipleChoice.initClubRecruitQuestion(this));
    }

    public void initClubApplyForm(ClubApplyForm clubApplyForm) {
        this.clubApplyForm = clubApplyForm;
        clubApplyForm.getClubRecruitQuestions().add(this);
    }

}
