package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameDuplicateException extends RuntimeException {
    public NameDuplicateException(String msg) {
        super(msg);
    }
}
