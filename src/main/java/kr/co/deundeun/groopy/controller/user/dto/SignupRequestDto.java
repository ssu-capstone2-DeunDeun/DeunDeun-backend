package kr.co.deundeun.groopy.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class SignupRequestDto {

    private String name;
    private String nickname;
    private String phoneNumber;
}
