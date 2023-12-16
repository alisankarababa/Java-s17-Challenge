package com.Javas17Challenge.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionIdea extends RuntimeException {

    private long timestamp;
    private HttpStatus status;

    public ExceptionIdea(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }
}
