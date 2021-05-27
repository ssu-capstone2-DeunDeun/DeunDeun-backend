package kr.co.deundeun.groopy.domain.clubApply;

import javax.persistence.FetchType;

import kr.co.deundeun.groopy.controller.clubApply.dto.ApplyAnswerDto;
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
public class ClubApplyAnswer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubApply clubApply;

    private String answer;

    public ClubApplyAnswer(ClubApply clubApply, String answer){
        this();
        setClubApply(clubApply);
        this.answer = answer;
    }

    public void setClubApply(ClubApply clubApply) {
        if (this.clubApply != null){
            this.clubApply.getClubApplyAnswers().remove(this);
        }
        this.clubApply = clubApply;
        clubApply.getClubApplyAnswers().add(this);
    }

    public void updateAnswer(String answer){
        this.answer = answer;
    }
}
