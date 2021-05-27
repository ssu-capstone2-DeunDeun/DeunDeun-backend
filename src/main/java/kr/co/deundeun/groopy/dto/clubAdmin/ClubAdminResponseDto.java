package kr.co.deundeun.groopy.dto.clubAdmin;

import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ClubAdminResponseDto {

    private Long id;
    private String nickname;
    private String name;
    private String phoneNumber;
    private String email;
    private int generation;
    private String positionName;
    private boolean isAdmin;

    @Builder
    public ClubAdminResponseDto(Participate participate) {
        User user = participate.getUser();
        this.id = participate.getId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.generation = participate.getClubRecruit().getGeneration();
        if (participate.getClubPosition() == null)
            this.positionName = "미정";
        else
            this.positionName = participate.getClubPosition().getPositionName();
        this.isAdmin = participate.isAdmin();
    }

    public static ClubAdminResponseDto of(Participate participate) {
        return ClubAdminResponseDto.builder().participate(participate).build();
    }

}
