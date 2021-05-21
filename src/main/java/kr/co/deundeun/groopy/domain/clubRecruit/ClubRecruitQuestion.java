package kr.co.deundeun.groopy.domain.clubRecruit;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionRequestDto;
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
    private ClubRecruit clubRecruit;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String questionContent;

    public ClubRecruitQuestion(QuestionType questionType, String questionContent){
        this.questionType = questionType;
        this.questionContent = questionContent;
    }

    public void setClubRecruit(ClubRecruit clubRecruit) {
        if(this.clubRecruit != null)
            this.clubRecruit.getClubRecruitQuestions().remove(this);

        this.clubRecruit = clubRecruit;
        clubRecruit.getClubRecruitQuestions().add(this);
    }

    public boolean equals(RecruitQuestionRequestDto question){
        return question.getQuestionType().equals(questionType)
                && question.getQuestionContent().equals(questionContent);
    }


}
