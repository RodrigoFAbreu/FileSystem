package org.cdb.filesystem.dao.file;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cdb.filesystem.dao.file.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse
{
    @JsonFormat
    private int httpCode;
    @JsonFormat
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private ErrorEnum error;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
}
