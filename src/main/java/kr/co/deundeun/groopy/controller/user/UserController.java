package kr.co.deundeun.groopy.controller.user;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/nickname")
    public ResponseEntity<Boolean> isDuplicatedNickname(@RequestBody String nickname){
        return ResponseEntity.ok(userService.isDuplicatedNickname(nickname));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> registerUser(@Me UserPrincipal userPrincipal,
                                                        @RequestBody SignupRequestDto signupRequestDto){

        UserResponseDto userResponseDto = userService.signup(userPrincipal.getId(), signupRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> changeNickname(@Me UserPrincipal userPrincipal,
                                               @RequestBody String nickname){
        userService.changeNickname(userPrincipal.getId(), nickname);
        return ResponseEntity.ok().build();
    }


}
