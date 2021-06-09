package kr.co.deundeun.groopy.dto.clubPosition.participates;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PositionChangeDto {

    private List<Long> participateIds;

    private Long positionId;

}
