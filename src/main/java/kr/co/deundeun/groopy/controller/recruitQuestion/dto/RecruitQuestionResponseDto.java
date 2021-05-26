package kr.co.deundeun.groopy.controller.recruitQuestion.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RecruitQuestionResponseDto {

    private QuestionType questionType;

    private String questionContent;

    public RecruitQuestionResponseDto(ClubRecruitQuestion clubRecruitQuestion) {
        this.questionType = clubRecruitQuestion.getQuestionType();
        this.questionContent = clubRecruitQuestion.getQuestionContent();
    }
}
