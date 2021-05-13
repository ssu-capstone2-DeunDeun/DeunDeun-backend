package kr.co.deundeun.groopy.domain.clubApply;

import javax.persistence.FetchType;
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
public class ClubApplyAnswer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubApply clubApply;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String answerContent;
}
