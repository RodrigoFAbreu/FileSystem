package org.cdb.filesystem.controller;

import org.cdb.filesystem.api.FilesApi;
import org.cdb.filesystem.dao.file.*;
import org.cdb.filesystem.dao.file.enums.OrderEnum;
import org.cdb.filesystem.model.enums.FileType;
import org.cdb.filesystem.service.FilesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
        ApiFile apiFile = filesService.addFile(aFileAddRequest);
        ApiFileAddResponse response = new ApiFileAddResponse(apiFile);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiFileListResponse> listFiles(String owner, FileType fileType, String filename, OrderEnum orderByDate)
    {
        List<ApiFile> apiFile = filesService.listFiles(owner, fileType, filename, orderByDate);
        ApiFileListResponse response = new ApiFileListResponse(apiFile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiFileGetResponse> getFile(Long fileId)
    {
        ApiFile apiFile = filesService.getFile(fileId);
        ApiFileGetResponse response = new ApiFileGetResponse(apiFile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiFileGetDetailsResponse> getFileDetails(Long fileId)
    {
        ApiFullFile apiFullFile = filesService.getFileDetails(fileId);
        ApiFileGetDetailsResponse response = new ApiFileGetDetailsResponse(apiFullFile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiFileUpdateResponse> updateFile(Long fileId, ApiFileUpdateRequest fileUpdateRequest)
    {
        ApiFullFile apiFullFile = filesService.updateFile(fileId, fileUpdateRequest);
        ApiFileUpdateResponse response = new ApiFileUpdateResponse(apiFullFile);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteFile(Long fileId)
    {
        filesService.deleteFile(fileId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

