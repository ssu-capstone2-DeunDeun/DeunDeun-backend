package kr.co.deundeun.groopy.dto.clubApply;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.deundeun.groopy.domain.clubApply.ClubApply;
import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Getter;

@Getter
public class ApplyInfoDto {

  private Long clubApplyId;
  private ClubApplyStatus clubApplyStatus;
  private String email;
  private String nickname;
  private String phoneNumber;

  public ApplyInfoDto(ClubApply clubApply) {
    this.clubApplyId = clubApply.getId();
    this.clubApplyStatus = clubApply.getClubApplyStatus();

    User user = clubApply.getUser();
    this.email = user.getEmail();
    this.nickname = user.getNickname();
    this.phoneNumber = user.getPhoneNumber();
  }

  public static List<ApplyInfoDto> ofList(List<ClubApply> clubApplies) {
    return clubApplies.stream()
                      .map(ApplyInfoDto::new)
                      .collect(Collectors.toList());
  }
}
