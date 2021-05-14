package kr.co.deundeun.groopy.controller.user;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.config.UserPrincipal;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.domain.hashtag.Hashtag;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/nickname")
    public ResponseEntity<Boolean> isDuplicatedNickname(@RequestBody String nickname) {
        return ResponseEntity.ok(userService.isDuplicatedNickname(nickname));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> registerUser(@Me User user,
                                                        @RequestBody SignupRequestDto signupRequestDto) {

        UserResponseDto userResponseDto = userService.signup(user, signupRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> updateNickname(@Me User user,
                                               @RequestBody String nickname) {
        userService.updateNickname(user, nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hashtags")
    public ResponseEntity<Void> addHashtagList(@Me User user,
                                               @RequestBody List<String> hashtags) {
        userService.updateHashtags(user, hashtags);
        return ResponseEntity.ok().build();
    }


}
