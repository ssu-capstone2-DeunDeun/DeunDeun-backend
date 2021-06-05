package kr.co.deundeun.groopy.dto.evaluate;

import kr.co.deundeun.groopy.domain.clubApply.constant.ClubApplyStatus;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ClubApplyStatusDto {

    List<ClubApplyStatus> clubApplyStatus;

}
