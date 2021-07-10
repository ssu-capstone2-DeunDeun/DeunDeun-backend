package kr.co.deundeun.groopy.dto.clubPosition.participates;

import java.util.List;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PositionChangeResponseDto {
  private List<Long> participateIds;

  private Long clubId;

  private Long positionId;

  private String positionName;

  public PositionChangeResponseDto(List<Long> participateIds, ClubPosition clubPosition){
    this.participateIds = participateIds;
    this.clubId = clubPosition.getClub().getId();
    this.positionId = clubPosition.getId();
    this.positionName = clubPosition.getPositionName();
  }
}
