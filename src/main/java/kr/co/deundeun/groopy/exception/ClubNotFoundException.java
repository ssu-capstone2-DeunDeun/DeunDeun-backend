package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClubNotFoundException extends RuntimeException{

    public ClubNotFoundException(){super("존재하지 않는 동아리입니다.");}
}
