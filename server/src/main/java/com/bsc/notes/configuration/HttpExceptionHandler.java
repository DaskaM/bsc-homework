package com.bsc.notes.configuration;

import com.bsc.notes.exception.ApiError;
import com.bsc.notes.exception.ApiErrorException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class handles exceptions of certain types, processes them as response for the client.
 */
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<ApiError> handleConflict(HttpClientErrorException exception, WebRequest request) {
        final ApiError apiError = new ApiError(exception.getStatusCode(), exception.getMessage());
        return createApiError(apiError);
    }

    /**
     * Catching and processing all uncaught exceptions coming from the database.
     */
    @ExceptionHandler(value = DataAccessException.class)
    protected ResponseEntity<ApiError> handleDatabaseConflict(DataAccessException exception, WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.ACCEPTED, exception.getMessage());
        return createApiError(apiError);
    }

    /**
     * Catching and processing all thrown exceptions of type {@link ApiErrorException}.
     */
    @ExceptionHandler(value = ApiErrorException.class)
    protected ResponseEntity<ApiError> handleApiError(ApiErrorException exception, WebRequest request) {
        return createApiError(exception.getApiError());
    }

    private ResponseEntity<ApiError> createApiError(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
}


