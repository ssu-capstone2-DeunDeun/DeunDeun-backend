package kr.co.deundeun.groopy.dto.common.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class JwtRefreshResponseDto {

  private String jwtAccessToken;
  private String jwtRefreshToken;

  @Builder
  public JwtRefreshResponseDto(String jwtAccessToken, String jwtRefreshToken) {
    this.jwtAccessToken = jwtAccessToken;
    this.jwtRefreshToken = jwtRefreshToken;
  }
}
