package com.Javas17Challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


public class CourseExceptionResponse extends ExceptionResponse{

    public CourseExceptionResponse(String message, long timestamp, HttpStatus status) {
        super(message, timestamp, status);
    }
}
