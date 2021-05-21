package kr.co.deundeun.groopy.controller.recruitQuestion.dto;

import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.Getter;

import java.util.List;

@Getter
public class RecruitQuestionRequestDto {

    private QuestionType questionType;

    private String questionContent;

    public ClubRecruitQuestion toClubRecruitQuestion(){
        return new ClubRecruitQuestion(questionType, questionContent);
    }
}
