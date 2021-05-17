package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.hashtag.dto.HashtagResponseDto;
import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.dao.UserHashtagRepository;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.hashtag.UserHashtag;
import kr.co.deundeun.groopy.domain.user.User;
import kr.co.deundeun.groopy.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserHashtagRepository userHashtagRepository;
    private final HashtagService hashtagService;


    public boolean isDuplicatedNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public UserResponseDto signup(User user, SignupRequestDto signupRequestDto) {
        user.saveSignupInfo(signupRequestDto.getNickname(), signupRequestDto.getName(), signupRequestDto.getPhoneNumber());

        userRepository.save(user);
        return UserResponseDto.of(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return UserResponseDto.of(user);
    }

    @Transactional
    public void updateNickname(User user, String nickname) {
        if (isDuplicatedNickname(nickname))
            throw new DuplicateResourceException("중복된 닉네임입니다.");

        user.changeNickname(nickname);
    }

    @Transactional
    public void updateHashtags(User user, List<String> hashtags) {
        if (hashtags.size() < 3)
            throw new IllegalArgumentException("해시태그는 3개 이상 등록해야 합니다.");

        hashtagService.registerUserHashtags(user, hashtags);
    }

    @Transactional
    public List<HashtagResponseDto> getHashtags(User user) {
        return HashtagResponseDto.ofList(userHashtagRepository.findAllByUser(user));
    }
}
