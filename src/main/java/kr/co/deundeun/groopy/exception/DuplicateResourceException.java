package kr.co.deundeun.groopy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String msg, Throwable t) {
        super(msg, t);
    }

    public DuplicateResourceException(String msg) {
        super(msg);
    }

}
