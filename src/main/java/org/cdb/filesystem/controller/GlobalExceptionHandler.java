package org.cdb.filesystem.controller;

import org.apache.coyote.BadRequestException;
import org.cdb.filesystem.dto.file.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
//    {
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(UnauthorizedAccessException.class)
//    public ResponseEntity<ApiError> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request)
//    {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex, WebRequest request)
    {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception ex, WebRequest request)
    {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unxepected error occurred.");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
