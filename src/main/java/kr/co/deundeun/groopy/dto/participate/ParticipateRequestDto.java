package kr.co.deundeun.groopy.dto.participate;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ParticipateRequestDto {

    private Long userId;

    private String email;

    private String clubName;

}
