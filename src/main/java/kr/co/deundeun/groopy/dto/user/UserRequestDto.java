package kr.co.deundeun.groopy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    @Size(min = 2, max = 8)
    private String name;

    @NotBlank(message = "닉네임을 입력하세요.")
    @Size(min = 2, max = 8)
    private String nickname;

}
