package kr.co.deundeun.groopy.dto.clubApply;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.ClubApplyAnswer;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubRecruitQuestion;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApplyResponseDto {

    private String recruitTitle;

    private List<ApplyQAResponseDto> applyQAResponseDtos;

    public ApplyResponseDto(ClubApply clubApply) {

        ClubRecruit clubRecruit = clubApply.getClubRecruit();
        this.recruitTitle = clubRecruit.getTitle();

        List<ClubRecruitQuestion> clubRecruitQuestions = clubRecruit.getClubRecruitQuestions();
        List<ClubApplyAnswer> clubApplyAnswers = clubApply.getClubApplyAnswers();
        this.applyQAResponseDtos = ApplyQAResponseDto.listOf(clubRecruitQuestions, clubApplyAnswers);
    }

    //    public ApplyResponseDto(ClubApply clubApply){
//
//        ClubRecruit clubRecruit = clubApply.getClubRecruit();
//
//        this.applyId = clubApply.getId();
//        this.recruitId = clubRecruit.getId();
//        this.clubApplyStatus = clubApply.getClubApplyStatus();
//        if(clubApply.getClubApplyAnswers() != null)
//            this.applyAnswers = ApplyAnswerResponseDto
//                    .ofList(clubApply.getClubApplyAnswers(), clubRecruit);
//    }
}
