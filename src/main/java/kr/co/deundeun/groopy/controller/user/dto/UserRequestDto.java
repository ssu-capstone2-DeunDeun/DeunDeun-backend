package kr.co.deundeun.groopy.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    private String name;

    private String nickname;

    private String phoneNumber;

    private String userImageUrl;
}
