package kr.co.deundeun.groopy.dto.evaluate;

import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class EvaluateResponseDto {

    private Long clubApplyId;

    private Long userId;

    private String phoneNumber;

    private String name;

    private String nickname;

    private String email;

    private ClubApplyStatus clubApplyStatus;

    public EvaluateResponseDto(ClubApply clubApply){
        User user = clubApply.getUser();
        this.clubApplyId = clubApply.getId();
        this.userId = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.clubApplyStatus = clubApply.getClubApplyStatus();
    }
}
