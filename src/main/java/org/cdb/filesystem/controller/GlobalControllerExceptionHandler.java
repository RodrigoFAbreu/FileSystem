package org.cdb.filesystem.controller;

import lombok.extern.log4j.Log4j2;
import org.cdb.filesystem.dao.file.ApiErrorResponse;
import org.cdb.filesystem.dao.file.enums.ErrorEnum;
import org.cdb.filesystem.exception.FileNotFoundException;
import org.cdb.filesystem.exception.MissingRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class GlobalControllerExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex)
    {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, ErrorEnum.UNEXPECTED_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequiredException(MissingRequiredException ex)
    {
        //HttpStatus.BAD_REQUEST.value(), ExceptionMessage.MISSING_REQUIRED, HttpStatus.BAD_REQUEST, LocalDateTime.now()
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getHttpCode(), ex.getStatus(), ErrorEnum.MISSING_REQUIRED, ex.getTimestamp());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleFileNotFoundException(FileNotFoundException ex)
    {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(ex.getHttpCode(), ex.getStatus(), ErrorEnum.FILE_NOT_FOUND, ex.getTimestamp());
        return ResponseEntity.status(ex.getStatus()).body(apiErrorResponse);
    }
//    @ExceptionHandler(UnauthorizedAccessException.class)
//    public ResponseEntity<ApiError> handleUnauthorizedAccessException(UnauthorizedAccessException ex, WebRequest request)
//    {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
//    }
}
