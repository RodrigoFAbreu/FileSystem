package org.cdb.filesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends HandlerException
{
    public FileNotFoundException(int code, String message, HttpStatus status, LocalDateTime timestamp)
    {
        super(code, message, status, timestamp);
    }
}
