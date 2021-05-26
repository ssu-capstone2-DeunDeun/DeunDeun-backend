package kr.co.deundeun.groopy.controller.clubApplyForm.dto;

import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.Getter;

import java.util.List;

@Getter
public class RecruitQuestionRequestDto {

    private QuestionType questionType;

    private String questionContent;

    private List<MultipleChoiceRequestDto> multipleChoiceRequestDtos;

    public ClubRecruitQuestion toClubRecruitQuestion(ClubApplyForm clubApplyForm){
        return new ClubRecruitQuestion(clubApplyForm, this);
    }

    public static List<ClubRecruitQuestion> ofList(ClubApplyForm clubApplyForm, List<RecruitQuestionRequestDto> recruitQuestionRequestDtos){
        return recruitQuestionRequestDtos.stream()
                                  .map(recruitQuestionRequestDto -> recruitQuestionRequestDto.toClubRecruitQuestion(clubApplyForm))
                                  .collect(Collectors.toList());
    }
}
