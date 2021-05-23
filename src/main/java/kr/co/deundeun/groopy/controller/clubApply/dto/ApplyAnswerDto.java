package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.ClubApplyAnswer;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ApplyAnswerDto {
    private QuestionType questionType;
    private String question;

    public ClubApplyAnswer toClubApplyAnswer(ClubApply clubApply){
        return ClubApplyAnswer.builder()
                .clubApply(clubApply)
                .questionType(questionType)
                .answerContent(question)
                .build();
    }

    public static List<ApplyAnswerDto> ofList(List<ClubApplyAnswer> clubApplyAnswers){
        return clubApplyAnswers.stream()
                .map(answer -> new ApplyAnswerDto(answer.getQuestionType(), answer.getAnswerContent()))
                .collect(Collectors.toList());
    }
}
