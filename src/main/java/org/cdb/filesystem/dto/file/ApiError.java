package org.cdb.filesystem.dto.file;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApiError
{
    private Integer code;
    private String message;
}
