package kr.co.deundeun.groopy.common.dto;

import kr.co.deundeun.groopy.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ErrorResponseDto {

    private int code;
    private String message;

    public static ErrorResponseDto of(ErrorCode errorCode){
        return new ErrorResponseDto(errorCode.getStatus(), errorCode.getMessage());
    }

    public static ErrorResponseDto of(int code, String message){
        return new ErrorResponseDto(code, message);
    }

}
