package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.controller.user.dto.SignupRequestDto;
import kr.co.deundeun.groopy.controller.user.dto.UserResponseDto;
import kr.co.deundeun.groopy.dao.UserRepository;
import kr.co.deundeun.groopy.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean isDuplicatedNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public UserResponseDto signup(Long id, SignupRequestDto signupRequestDto){
        UserInfo userInfo = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userInfo.setName(signupRequestDto.getName());
        userInfo.setNickname(signupRequestDto.getNickname());
        userInfo.setPhoneNumber(signupRequestDto.getPhoneNumber());

        userRepository.save(userInfo);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setNickname(userInfo.getNickname());
        userResponseDto.setMsg("회원 가입 성공");
        return userResponseDto;
    }


}
