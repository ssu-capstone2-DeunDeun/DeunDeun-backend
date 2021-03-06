package kr.co.deundeun.groopy.controller.user;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.hashtag.HashtagResponseDto;
import kr.co.deundeun.groopy.dto.like.liked.LikeListResponseDto;
import kr.co.deundeun.groopy.dto.user.UserRequestDto;
import kr.co.deundeun.groopy.dto.user.UserResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.dto.user.userClub.UserClubDto;
import kr.co.deundeun.groopy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/nickname")
    public ResponseEntity<Boolean> isDuplicatedNickname(@RequestParam String nickname) {
        return ResponseEntity.ok(userService.isDuplicatedNickname(nickname));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Me User user,
                                                        @Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.signup(user, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserInfo(@Me User user) {
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(@Me User user, @RequestParam String nickname) {
        return ResponseEntity.ok(userService.updateNickname(user, nickname));
    }

    @PutMapping("/image")
    public ResponseEntity<Void> updateUserImageUrl(@Me User user, @RequestParam String userImageUrl) {
        userService.updateUserImageUrl(user, userImageUrl);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hashtags")
    public ResponseEntity<Void> addHashtags(@Me User user, @RequestBody List<Long> hashtagInfoIds) {
        userService.updateHashtags(user, hashtagInfoIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hashtags")
    public ResponseEntity<List<HashtagResponseDto>> getHashtags(@Me User user) {
        return ResponseEntity.ok(userService.getHashtags(user));
    }

    @GetMapping("/likes")
    public ResponseEntity<LikeListResponseDto> getLikes(@Me User user){
        return ResponseEntity.ok(userService.getLikes(user));
    }

    @GetMapping("/clubs")
    public ResponseEntity<List<UserClubDto>> getClubs(@Me User user){
        return ResponseEntity.ok(userService.getClubs(user));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@Me User user){
        userService.deleteUser(user);
        return ResponseEntity.ok("????????? ??????????????????.");
    }
}
