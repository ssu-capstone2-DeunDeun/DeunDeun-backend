package kr.co.deundeun.groopy.common.advice;

import kr.co.deundeun.groopy.common.ErrorCode;
import kr.co.deundeun.groopy.common.dto.ErrorResponseDto;
import kr.co.deundeun.groopy.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ErrorResponseDto> loginException(LoginException e){
        return ResponseEntity.status(HttpStatus
                .UNAUTHORIZED).body(ErrorResponseDto.of(ErrorCode.LOGIN_ERROR));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponseDto> defaultException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
