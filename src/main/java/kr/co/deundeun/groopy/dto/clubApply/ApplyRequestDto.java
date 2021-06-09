package kr.co.deundeun.groopy.dto.clubApply;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubRecruit.ClubRecruit;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
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
