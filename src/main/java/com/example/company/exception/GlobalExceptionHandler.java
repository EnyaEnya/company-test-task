package com.example.company.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDetails handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(false));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDetails handle(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(true));
    }
}
