package com.bsc.notes.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

    private HttpStatus statusCode;
    private String errorMessage;

    public ApiError(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public ApiError(HttpStatus statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

