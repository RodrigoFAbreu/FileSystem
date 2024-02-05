package org.cdb.filesystem.dao.file.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorEnum
{
    UNEXPECTED_ERROR(1, "An unexpected error occurred.", ""),
    ENTITY_SAVED_SUCCESSFULLY(2, "Entity saved successfully", ""),
    ENTITY_UPDATED_SUCCESSFULLY(3, "Entity updated successfully", ""),
    ENTITY_DELETED_SUCCESSFULLY(4, "Entity deleted successfully", ""),
    MISSING_REQUIRED(5, "Missing required fields", ""),
    FILE_NOT_FOUND(6, "File not found", ""),
    EXPIRED_TOKEN(7, "Invalid token", ""),
    INVALID_TOKEN(8, "Invalid token", ""),
    INVALID_CREDENTIALS(9, "Invalid credentials", ""),
    ACCESS_DENIED(10, "Access denied", "Sorry, you do not have permissions to access this resource."),
    NOT_AUTHENTICATION(11, "Missing credentials", "No authentication credentials found");

    private final Integer code;
    private final String shortMessage;
    private final String longMessage;

    ErrorEnum(Integer code, String shortMessage, String longMessage)
    {
        this.code = code;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;
    }
}
