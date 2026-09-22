package com.ebs.biocrop.exception;

import org.springframework.http.HttpStatus;

public class InvalidOtpException extends AppException {

    public InvalidOtpException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
