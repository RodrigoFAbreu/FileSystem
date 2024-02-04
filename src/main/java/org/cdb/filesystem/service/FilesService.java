package org.cdb.filesystem.service;


import org.cdb.filesystem.dto.file.ApiFile;
import org.cdb.filesystem.dto.file.ApiFileAddRequest;
import org.cdb.filesystem.dto.file.ApiFullFile;
import org.springframework.stereotype.Component;

@Component
public interface FilesService
{
    ApiFile addFile(ApiFileAddRequest fileAddRequest);

    ApiFile getFileById(Long aFileId);

    ApiFullFile getFileDetailsById(Long aFileId);
}
