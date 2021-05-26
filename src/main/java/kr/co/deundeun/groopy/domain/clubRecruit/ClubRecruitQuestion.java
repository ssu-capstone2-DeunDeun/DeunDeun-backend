package kr.co.deundeun.groopy.domain.clubRecruit;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionRequestDto;
import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
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

    /*
    주관식인 경우 : null 값
    객관식인 경우 : 선택지 저장
     */
    @OneToMany(mappedBy = "clubRecruitQuestion")
    @OrderBy("choiceNumber")
    private List<MultipleChoice> multipleChoices = new ArrayList<>();

    public ClubRecruitQuestion(QuestionType questionType, String questionContent){
        this.questionType = questionType;
        this.questionContent = questionContent;
    }

    public void setClubApplyForm(ClubApplyForm clubApplyForm) {
        if (this.clubApplyForm != null) {
            this.clubApplyForm.getClubRecruitQuestions().remove(this);
        }
        this.clubApplyForm = clubApplyForm;
        clubApplyForm.getClubRecruitQuestions().add(this);
    }

    public boolean equals(RecruitQuestionRequestDto question){
        return question.getQuestionType().equals(questionType)
                && question.getQuestionContent().equals(questionContent);
    }


}
