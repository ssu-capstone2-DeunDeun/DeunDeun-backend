package kr.co.deundeun.groopy.controller.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    private String name;
    private String nickname;
    private String phoneNumber;
}
