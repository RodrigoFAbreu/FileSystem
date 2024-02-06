package org.cdb.filesystem.dao.file;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiFileUpdateRequest extends ApiFileCommon
{
    @Lob
    private String data;
}
