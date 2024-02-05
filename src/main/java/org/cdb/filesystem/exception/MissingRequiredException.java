package org.cdb.filesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingRequiredException extends HandlerException
{
    public MissingRequiredException(int code, String message, HttpStatus status, LocalDateTime timestamp)
    {
        super(code, message, status, timestamp);
    }
}