package kr.co.deundeun.groopy.controller.recruitQuestion.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecruitQuestionResponseDto {

    private QuestionType questionType;

    private String questionContent;

    public static RecruitQuestionResponseDto of(ClubRecruitQuestion question) {
        return new RecruitQuestionResponseDto(question.getQuestionType(), question.getQuestionContent());
    }
}
