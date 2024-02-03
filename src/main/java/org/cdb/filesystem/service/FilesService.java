package org.cdb.filesystem.service;


import org.cdb.filesystem.dto.file.ApiFile;
import org.cdb.filesystem.dto.file.ApiFileAddRequest;
import org.springframework.stereotype.Component;

@Component
public interface FilesService
{
    ApiFile addFile(ApiFileAddRequest fileAddRequest);

    ApiFile getFileById(Long aFileId);
}
