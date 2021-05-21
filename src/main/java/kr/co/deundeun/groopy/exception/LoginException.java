package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException {
    public LoginException() {
        super("로그인이 필요합니다.");
    }
}
