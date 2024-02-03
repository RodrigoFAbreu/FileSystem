package org.cdb.filesystem.model.enums;

import lombok.Getter;

@Getter
public enum FileType
{
    JPEG("image/jpeg", "jpeg"),
    PNG("image/png", "png"),
    PDF("application/pdf", "pdf");
    // Add more file types as needed

    private final String mimeType;
    private final String extension;

    FileType(String mimeType, String extension)
    {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    // Utility method to get FileType by mimeType or extension
    public static FileType fromString(String text)
    {
        for (FileType type : FileType.values())
        {
            if (type.mimeType.equalsIgnoreCase(text) || type.extension.equalsIgnoreCase(text))
            {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public String getExtension()
    {
        return extension;
    }

    public String getMimeType()
    {
        return mimeType;
    }
}
