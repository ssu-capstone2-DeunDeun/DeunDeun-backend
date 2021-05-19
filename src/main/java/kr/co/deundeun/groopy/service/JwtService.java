package kr.co.deundeun.groopy.service;

import kr.co.deundeun.groopy.config.security.JwtTokenProvider;
import kr.co.deundeun.groopy.controller.jwt.dto.JwtRefreshResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtService {

  private final JwtTokenProvider jwtTokenProvider;

  public JwtRefreshResponseDto refreshJwt(String jwtRefreshToken){

    if(!jwtTokenProvider.validateToken(jwtRefreshToken, "REFRESH_TOKEN")){
      throw new IllegalArgumentException("유효한 REFRESH_TOKEN 이 아닙니다.");
    }

    Long userId = jwtTokenProvider.getUserIdFromToken(jwtRefreshToken);

    String reissuedJwtAccessToken = jwtTokenProvider.createAccessToken(userId);
    String reissuedJwtRefreshToken = getReissuedJwtRefreshToken(jwtRefreshToken, userId);

    return JwtRefreshResponseDto.builder()
                                .jwtAccessToken(reissuedJwtAccessToken)
                                .jwtRefreshToken(reissuedJwtRefreshToken)
                                .build();
  }

  private String getReissuedJwtRefreshToken(String jwtRefreshToken, Long userId) {

    String reissuedJwtRefreshToken = jwtRefreshToken;

    if (jwtTokenProvider.calculateDaysLeft(jwtRefreshToken) < 3){
      reissuedJwtRefreshToken = jwtTokenProvider.createRefreshToken(userId);
    }
    return reissuedJwtRefreshToken;
  }
}
