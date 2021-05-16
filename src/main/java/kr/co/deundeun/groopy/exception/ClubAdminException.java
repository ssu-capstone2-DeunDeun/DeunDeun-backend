package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ClubAdminException extends RuntimeException{
    public ClubAdminException(){super("동아리 관리자 권한이 필요합니다.");}
}
