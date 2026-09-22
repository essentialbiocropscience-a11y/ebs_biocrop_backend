package com.ebs.biocrop.exception;

import org.springframework.http.HttpStatus;

public class OtpCooldownException extends AppException {

    private final long remainingSeconds;

    public OtpCooldownException(String message, long remainingSeconds) {
        super(message, HttpStatus.TOO_MANY_REQUESTS);
        this.remainingSeconds = remainingSeconds;
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }
}
