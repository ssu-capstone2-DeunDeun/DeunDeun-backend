package kr.co.deundeun.groopy.dto.user.userClub;

import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserClubDto {

    private ClubResponseDto clubResponseDto;

    private boolean isAdmin;

    public UserClubDto(ClubResponseDto clubResponseDto, boolean isAdmin) {
        this.clubResponseDto = clubResponseDto;
        this.isAdmin = isAdmin;
    }

    public static List<UserClubDto> ofList(List<Participate> participates) {
        return participates.stream()
                .map(participate -> new UserClubDto(ClubResponseDto.of(participate.getClub()), participate.isAdmin()))
                .collect(Collectors.toList());
    }
}
