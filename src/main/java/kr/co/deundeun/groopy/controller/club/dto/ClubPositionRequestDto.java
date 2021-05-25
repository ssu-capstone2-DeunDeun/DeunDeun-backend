package kr.co.deundeun.groopy.controller.club.dto;

import kr.co.deundeun.groopy.domain.club.Club;
import kr.co.deundeun.groopy.domain.club.ClubPosition;
import lombok.Getter;

@Getter
public class ClubPositionRequestDto {
    private String positionName;

    public ClubPosition toClubPosition(Club club){
        return ClubPosition.builder()
                .club(club)
                .positionName(positionName)
                .build();
    }
}
