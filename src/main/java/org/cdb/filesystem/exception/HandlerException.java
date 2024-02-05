package org.cdb.filesystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.cdb.filesystem.dao.file.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HandlerException extends RuntimeException
{
    private int httpCode;
    private HttpStatus status;
    private ErrorEnum error;
    private LocalDateTime timestamp;

}
