package kr.co.deundeun.groopy.controller.user.dto;

import kr.co.deundeun.groopy.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponseDto {
    private String name;

    private String nickname;

    private String phoneNumber;

    private String email;

    private String userImageUrl;

    public static UserResponseDto of(User user){
        return new UserResponseDto(user.getName(), user.getNickname(), user.getPhoneNumber(), user.getEmail(), user.getUserImageUrl());
    }
}
