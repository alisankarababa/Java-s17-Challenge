package com.Javas17Challenge.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

public class CourseException extends ExceptionIdea {


    public CourseException(String message, HttpStatus status) {
        super(message, status);
    }
}
