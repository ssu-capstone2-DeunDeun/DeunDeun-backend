package kr.co.deundeun.groopy.dto.user;

import kr.co.deundeun.groopy.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
public class UserResponseDto {

    private Long userId;

    private String name;

    private String nickname;

    private String phoneNumber;

    private String email;

    private String userImageUrl;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.userImageUrl = user.getUserImageUrl();
    }
}
