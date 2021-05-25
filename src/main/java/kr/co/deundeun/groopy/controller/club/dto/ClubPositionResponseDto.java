package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.domain.club.ClubPosition;

import java.util.List;
import java.util.stream.Collectors;

public class ClubPositionResponseDto {

    private Long id;

    private String positionName;

    public ClubPositionResponseDto(ClubPosition clubPosition){
        this.id = clubPosition.getId();
        this.positionName = clubPosition.getPositionName();
    }

    public static List<ClubPositionResponseDto> listOf(List<ClubPosition> clubPositions){
        return clubPositions.stream()
                .map(ClubPositionResponseDto::new)
                .collect(Collectors.toList());
    }

}
