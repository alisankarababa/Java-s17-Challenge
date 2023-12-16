package com.Javas17Challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private long timestamp;
    private HttpStatus status;
}
