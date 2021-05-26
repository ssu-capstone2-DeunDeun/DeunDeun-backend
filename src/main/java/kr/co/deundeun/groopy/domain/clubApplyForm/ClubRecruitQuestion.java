package kr.co.deundeun.groopy.domain.clubApplyForm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import kr.co.deundeun.groopy.controller.clubApplyForm.dto.MultipleChoiceRequestDto;
import kr.co.deundeun.groopy.controller.clubApplyForm.dto.RecruitQuestionRequestDto;
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
public class ClubRecruitQuestion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubApplyForm clubApplyForm;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String questionContent;

    /*
    주관식인 경우 : 빈 리스트 저장
    객관식인 경우 : 선택지 저장
     */
    @OneToMany(mappedBy = "clubRecruitQuestion")
    @OrderBy("choiceNumber")
    private List<MultipleChoice> multipleChoices = new ArrayList<>();

    public ClubRecruitQuestion(ClubApplyForm clubApplyForm, RecruitQuestionRequestDto recruitQuestionRequestDto) {
        this();
        setClubApplyForm(clubApplyForm);
        this.questionType = recruitQuestionRequestDto.getQuestionType();
        this.questionContent = recruitQuestionRequestDto.getQuestionContent();
        if (this.questionType.equals(QuestionType.MULTIPLE)) {
            this.multipleChoices = MultipleChoiceRequestDto.ofList(this, recruitQuestionRequestDto.getMultipleChoiceRequestDtos());
        }
        //            recruitQuestionRequestDto.getMultipleChoiceRequestDtos()
//                                                        .stream()
//                                                        .map(multipleChoiceRequestDto -> new MultipleChoice(this, multipleChoiceRequestDto))
//                                                        .collect(Collectors.toList());

    }

    public void setClubApplyForm(ClubApplyForm clubApplyForm) {
        if (this.clubApplyForm != null) {
            this.clubApplyForm.getClubRecruitQuestions().remove(this);
        }
        this.clubApplyForm = clubApplyForm;
        clubApplyForm.getClubRecruitQuestions().add(this);
    }

    public boolean equals(RecruitQuestionRequestDto question){
        return question.getQuestionType().equals(questionType)
                && question.getQuestionContent().equals(questionContent);
    }


}
