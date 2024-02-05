package org.cdb.filesystem.exception;

import org.cdb.filesystem.dao.file.enums.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingRequiredException extends HandlerException
{
    public MissingRequiredException(int httpCode, HttpStatus status, ErrorEnum error, LocalDateTime timestamp)
    {
        super(httpCode, status, error, timestamp);
    }
}