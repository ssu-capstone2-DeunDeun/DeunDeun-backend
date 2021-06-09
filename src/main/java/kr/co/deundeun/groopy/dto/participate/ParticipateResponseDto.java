package kr.co.deundeun.groopy.dto.participate;

import kr.co.deundeun.groopy.domain.user.Participate;
import kr.co.deundeun.groopy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipateResponseDto {

    private Long id;
    private Long userId;
    private String nickname;
    private String name;
    private String phoneNumber;
    private String email;
    private int generation;
    private String positionName;
    private boolean isAdmin;

    @Builder
    public ParticipateResponseDto(Participate participate) {
        User user = participate.getUser();
        this.id = participate.getId();
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.generation = participate.getGeneration();
        if (participate.getClubPosition() == null)
            this.positionName = "미정";
        else
            this.positionName = participate.getClubPosition().getPositionName();
        this.isAdmin = participate.isAdmin();
    }

    public static ParticipateResponseDto of(Participate participate) {
        return ParticipateResponseDto.builder().participate(participate).build();
    }

}
