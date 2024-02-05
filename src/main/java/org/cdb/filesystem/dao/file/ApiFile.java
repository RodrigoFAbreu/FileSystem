package org.cdb.filesystem.dao.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiFile extends ApiFileCommon
{
    private Long id;
    private Integer fileSize;
    private String owner;
    private Date creationDate;
    private Date updateDate;
    private Date deleteDate;
}
