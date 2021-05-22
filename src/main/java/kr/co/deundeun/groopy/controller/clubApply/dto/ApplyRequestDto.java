package kr.co.deundeun.groopy.controller.clubApply.dto;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.LoginException;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplyRequestDto {

    private Long clubRecruitId;

    private List<String> applyAnswers;

    public ClubApply toClubApply(User user){
        return ClubApply.builder()
                .user(user)
                .clubRecruitId(clubRecruitId)
                .build();
    }

}
