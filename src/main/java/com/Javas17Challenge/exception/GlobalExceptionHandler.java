package com.Javas17Challenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CourseExceptionResponse> hCourseException(CourseException courseException) {

        log.error(String.format("CourseException message: %s", courseException.getMessage()));

        CourseExceptionResponse courseExceptionResponse = new CourseExceptionResponse(courseException.getMessage(), courseException.getTimestamp(), courseException.getStatus());

        return new ResponseEntity<>(courseExceptionResponse, courseException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> hException(Exception exception) {
        log.error(String.format("Exception message: %s", exception.getMessage()));

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR);



        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }
}
