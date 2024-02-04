package org.cdb.filesystem.service;


import org.cdb.filesystem.dto.file.ApiFile;
import org.cdb.filesystem.dto.file.ApiFileAddRequest;
import org.cdb.filesystem.dto.file.ApiFullFile;
import org.cdb.filesystem.dto.file.enums.Order;
import org.cdb.filesystem.model.enums.FileType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FilesService
{
    ApiFile addFile(ApiFileAddRequest fileAddRequest);

    ApiFile getFileById(Long aFileId);

    ApiFullFile getFileDetailsById(Long aFileId);

    List<ApiFile> listFiles(String owner, FileType fileType, String filename, Order orderByDate);
}
