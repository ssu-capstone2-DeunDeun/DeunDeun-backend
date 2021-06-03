package kr.co.deundeun.groopy.dto.clubPosition.participates;

import lombok.Getter;

import java.util.List;

@Getter
public class PositionChangeDto {

    private List<Long> participateIds;

    private Long positionId;

}
