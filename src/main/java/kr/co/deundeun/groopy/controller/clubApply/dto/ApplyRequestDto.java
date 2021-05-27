package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.dao.ClubRecruitRepository;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApplyForm.ClubApplyForm;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.helper.ClubRecruitHelper;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplyRequestDto {

    private List<String> applyAnswers;

    public ClubApply toClubApply(User user, ClubRecruit clubRecruit){
        return ClubApply.builder()
                        .user(user)
                        .clubRecruit(clubRecruit)
                        .clubApplyAnswers(applyAnswers)
                        .build();
    }
}
