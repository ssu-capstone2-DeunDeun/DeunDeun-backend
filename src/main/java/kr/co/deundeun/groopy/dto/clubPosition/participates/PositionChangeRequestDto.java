package kr.co.deundeun.groopy.dto.clubPosition.participates;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PositionChangeRequestDto {

    private List<Long> participateIds;

    private Long positionId;

}
