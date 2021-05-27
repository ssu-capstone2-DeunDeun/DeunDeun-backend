package kr.co.deundeun.groopy.controller;

import kr.co.deundeun.groopy.dto.common.jwt.JwtRefreshResponseDto;
import kr.co.deundeun.groopy.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtController {

  private final JwtService jwtService;

  @PostMapping("/jwt/refresh")
  public ResponseEntity<JwtRefreshResponseDto> refreshJwt(@RequestParam String jwtRefreshToken){
    JwtRefreshResponseDto jwtRefreshResponseDto = jwtService.refreshJwt(jwtRefreshToken);
    return ResponseEntity.ok(jwtRefreshResponseDto);
  }

}
