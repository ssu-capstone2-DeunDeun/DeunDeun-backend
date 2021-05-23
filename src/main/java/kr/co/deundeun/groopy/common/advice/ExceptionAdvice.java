package kr.co.deundeun.groopy.common.advice;

import jogamp.nativewindow.x11.X11Util;
import kr.co.deundeun.groopy.common.ErrorCode;
import kr.co.deundeun.groopy.common.dto.ErrorResponseDto;
import kr.co.deundeun.groopy.exception.BadRequestException;
import kr.co.deundeun.groopy.exception.IdNotFoundException;
import kr.co.deundeun.groopy.exception.LoginException;
import kr.co.deundeun.groopy.exception.NameDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ErrorResponseDto> loginException(LoginException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.of(ErrorCode.LOGIN_ERROR));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponseDto> defaultException(Exception e){
        log.warn(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<ErrorResponseDto> tokenException(SignatureException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDto.of(ErrorCode.TOKEN_ERROR));
    }

    @ExceptionHandler(IdNotFoundException.class)
    protected ResponseEntity<ErrorResponseDto> idNotFoundException(IdNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorResponseDto> badRequestException(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST.value(),e.getMessage()));
    }

    @ExceptionHandler(NameDuplicateException.class)
    protected ResponseEntity<ErrorResponseDto> nameDuplicationException(NameDuplicateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponseDto.of(HttpStatus.CONFLICT.value(),e.getMessage()));
    }
}
