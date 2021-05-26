package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.ClubApplyAnswer;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ApplyAnswerDto {

    private int order;
    private QuestionType questionType;
    private String question;
    private String answer;

    public ClubApplyAnswer toClubApplyAnswer(ClubApply clubApply) {
        return ClubApplyAnswer.builder()
                .clubApply(clubApply)
                .questionType(questionType)
                .answerContent(answer)
                .build();
    }

    @Builder
    public ApplyAnswerDto(int order, ClubApplyAnswer clubApplyAnswer, ClubRecruitQuestion clubRecruitQuestion){
        this.order = order;
        this.questionType = clubRecruitQuestion.getQuestionType();
        this.question = clubRecruitQuestion.getQuestionContent();
        this.answer = clubApplyAnswer.getAnswerContent();
    }

    public static List<ApplyAnswerDto> ofList(List<ClubApplyAnswer> clubApplyAnswers, ClubRecruit clubRecruit) {
        List<ClubRecruitQuestion> clubRecruitQuestions = clubRecruit.getClubRecruitQuestions();

        AtomicInteger index = new AtomicInteger();

        return clubApplyAnswers.stream()
                .map(answer -> ApplyAnswerDto.builder()
                        .order(index.get() + 1)
                        .clubApplyAnswer(answer)
                        .clubRecruitQuestion(clubRecruitQuestions.get(index.getAndIncrement()))
                        .build())
                        .collect(Collectors.toList());
    }
}
