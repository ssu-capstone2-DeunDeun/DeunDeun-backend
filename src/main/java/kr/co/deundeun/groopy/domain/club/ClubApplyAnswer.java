package kr.co.deundeun.groopy.domain.club;

import kr.co.deundeun.groopy.domain.BaseEntity;
import kr.co.deundeun.groopy.domain.club.constant.QuestionType;
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

    @ManyToOne
    private ClubApply clubApply;

    private String answerContent;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
}
