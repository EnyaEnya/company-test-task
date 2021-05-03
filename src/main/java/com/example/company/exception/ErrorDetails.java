package com.example.company.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
public class ErrorDetails {
    private Instant timestamp;
    private String message;
    private String details;

    public ErrorDetails(Instant timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
