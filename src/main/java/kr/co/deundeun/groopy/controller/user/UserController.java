package kr.co.deundeun.groopy.controller.user;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.controller.hashtag.dto.HashtagResponseDto;
import kr.co.deundeun.groopy.controller.like.dto.LikeResponseDto;
import kr.co.deundeun.groopy.controller.user.dto.LikeListResponseDto;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Boolean> isDuplicatedNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(userService.isDuplicatedNickname(nickname));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Me User user,
                                                        @RequestBody SignupRequestDto signupRequestDto) {
        UserResponseDto userResponseDto = userService.signup(user, signupRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<UserResponseDto> getUserInfo(@Me User user) {
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @PatchMapping("/{nickname}")
    public ResponseEntity<Void> updateNickname(@Me User user, @PathVariable String nickname) {
        userService.updateNickname(user, nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{nickname}/hashtags")
    public ResponseEntity<Void> addHashtags(@Me User user,
                                            @RequestBody List<String> hashtags) {
        userService.updateHashtags(user, hashtags);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{nickname}/hashtags")
    public ResponseEntity<List<HashtagResponseDto>> getHashtags(@Me User user) {
        return ResponseEntity.ok(userService.getHashtags(user));
    }

    @GetMapping("/{nicknames}/likes")
    public ResponseEntity<LikeListResponseDto> getLikes(@Me User user){
        return ResponseEntity.ok(userService.getLikes(user));
    }

}
