package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.stream.Collectors;

import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubApplyForm.MultipleChoice;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class RecruitQuestionRequestDto {

    private QuestionType questionType;

    private String questionContent;

    private int questionNumber;

    private List<MultipleChoiceRequestDto> multipleChoiceRequestDtos;

    public ClubRecruitQuestion toEntity() {

        if (this.questionType.equals(QuestionType.SUBJECTIVE)) {
            return toSubjectiveTypeEntity();
        }
        return toMultipleTypeEntity();
    }

    private ClubRecruitQuestion toSubjectiveTypeEntity() {
        return ClubRecruitQuestion.builder()
                .questionType(QuestionType.SUBJECTIVE)
                .questionContent(questionContent)
                .questionNumber(questionNumber)
                .build();
    }

    private ClubRecruitQuestion toMultipleTypeEntity() {
        List<MultipleChoice> multipleChoices = MultipleChoiceRequestDto.toEntityList(multipleChoiceRequestDtos);
        return ClubRecruitQuestion.builder()
                .questionType(QuestionType.MULTIPLE)
                .questionContent(questionContent)
                .questionNumber(questionNumber)
                .multipleChoices(multipleChoices)
                .build();
    }

    public static List<ClubRecruitQuestion> toEntityList(List<RecruitQuestionRequestDto> recruitQuestionRequestDtos) {
        return recruitQuestionRequestDtos.stream()
                .map(RecruitQuestionRequestDto::toEntity)
                .collect(Collectors.toList());
    }
}
