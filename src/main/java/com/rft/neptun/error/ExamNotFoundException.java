package com.rft.neptun.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent when exam cannot be found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExamNotFoundException extends RuntimeException{

    public ExamNotFoundException(String message) {
        super(message);
    }
}
