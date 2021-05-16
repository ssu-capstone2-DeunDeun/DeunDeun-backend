package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClubRecruitNotFoundException extends RuntimeException {
    public ClubRecruitNotFoundException(){super("존재하지 않는 동아리 지원서입니다.");}
}
