package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.config.Me;
import kr.co.deundeun.groopy.dto.club.ClubResponseDto;
import kr.co.deundeun.groopy.dto.hashtag.HashtagResponseDto;
import kr.co.deundeun.groopy.dto.liked.LikeListResponseDto;
import kr.co.deundeun.groopy.dto.user.UserRequestDto;
import kr.co.deundeun.groopy.dto.user.UserResponseDto;
import kr.co.deundeun.groopy.domain.user.User;
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
    public ResponseEntity<Void> updateNickname(@Me User user,
                                               @Valid @RequestBody UserRequestDto userRequestDto) {
        userService.updateNickname(user, userRequestDto.getNickname());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/image")
    public ResponseEntity<Void> updateUserImageUrl(@Me User user, @RequestBody UserRequestDto userRequestDto) {
        userService.updateUserImageUrl(user, userRequestDto.getUserImageUrl());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hashtags")
    public ResponseEntity<Void> addHashtags(@Me User user, @RequestBody List<String> hashtags) {
        userService.updateHashtags(user, hashtags);
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
    public ResponseEntity<List<ClubResponseDto>> getClubs(@Me User user){
        return ResponseEntity.ok(userService.getClubs(user));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@Me User user){
        userService.deleteUser(user);
        return ResponseEntity.ok("회원이 탈퇴했습니다.");
    }
}
