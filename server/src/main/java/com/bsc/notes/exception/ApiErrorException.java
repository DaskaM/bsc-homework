package com.bsc.notes.exception;

/**
 * This runtime exception {@link RuntimeException} should be thrown whenever
 * there's an error we want to communicate to the client
 */
public class ApiErrorException extends RuntimeException {

    private ApiError apiError;

    public ApiErrorException(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
