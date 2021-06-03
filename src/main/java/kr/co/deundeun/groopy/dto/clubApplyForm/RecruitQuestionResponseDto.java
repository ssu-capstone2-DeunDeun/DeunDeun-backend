package kr.co.deundeun.groopy.dto.clubApplyForm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.constant.QuestionType;
import lombok.Getter;

@Getter
public class RecruitQuestionResponseDto {

    private QuestionType questionType;

    private String questionContent;

    private List<MultipleChoiceResponseDto> multipleChoiceResponseDtos = new ArrayList<>();

    public RecruitQuestionResponseDto(ClubRecruitQuestion clubRecruitQuestion) {
        this.questionType = clubRecruitQuestion.getQuestionType();
        this.questionContent = clubRecruitQuestion.getQuestionContent();
        if (this.questionType.equals(QuestionType.MULTIPLE)){
            this.multipleChoiceResponseDtos = MultipleChoiceResponseDto.listOf(clubRecruitQuestion.getMultipleChoices());
        }
    }

    public static List<RecruitQuestionResponseDto> listOf(List<ClubRecruitQuestion> clubRecruitQuestions){
        return clubRecruitQuestions.stream()
                                   .map(RecruitQuestionResponseDto::new)
                                   .collect(Collectors.toList());
    }
}
