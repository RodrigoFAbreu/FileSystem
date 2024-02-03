package org.cdb.filesystem.controller;

import org.cdb.filesystem.api.FilesApi;
import org.cdb.filesystem.dto.file.*;
import org.cdb.filesystem.service.FilesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filesystem/v1/")
public class FilesController implements FilesApi
{

    @Autowired
    private final FilesServiceImpl filesService;

    public FilesController(FilesServiceImpl aFilesService)
    {
        this.filesService = aFilesService;
    }

    @Override
    public ResponseEntity<ApiFileAddResponse> addFile(ApiFileAddRequest aFileAddRequest)
    {
        ApiFile addedFile = filesService.addFile(aFileAddRequest);
        ApiFileAddResponse response = new ApiFileAddResponse(addedFile);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiFileListResponse> listFiles(String owner, String fileType, String filename, String orderByDate)
    {
        return null;
    }

    @Override
    public ResponseEntity<ApiFileGetResponse> getFile(Integer fileId)
    {
        return null;
    }

    @Override
    public ResponseEntity<ApiFileGetDetailsResponse> getFileDetails(Integer fileId)
    {
        return null;
    }

    @Override
    public ResponseEntity<ApiFileUpdateResponse> updateFile(Integer fileId, ApiFileUpdateRequest fileUpdateRequest)
    {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteFile(Integer fileId)
    {
        return null;
    }
}
