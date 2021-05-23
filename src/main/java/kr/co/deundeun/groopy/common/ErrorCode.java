package kr.co.deundeun.groopy.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(405, "사용불가능한 메소드입니다."),
    LOGIN_ERROR(401, "로그인이 필요합니다."),
    TOKEN_ERROR(401, "잘못된 인증 토큰입니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
