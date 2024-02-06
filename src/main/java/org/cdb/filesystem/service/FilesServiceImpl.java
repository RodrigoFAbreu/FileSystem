package org.cdb.filesystem.service;

import lombok.extern.log4j.Log4j2;
import org.cdb.filesystem.dao.file.ApiFile;
import org.cdb.filesystem.dao.file.ApiFileAddRequest;
import org.cdb.filesystem.dao.file.ApiFullFile;
import org.cdb.filesystem.dao.file.enums.ErrorEnum;
import org.cdb.filesystem.dao.file.enums.OrderEnum;
import org.cdb.filesystem.exception.FileNotFoundException;
import org.cdb.filesystem.model.File;
import org.cdb.filesystem.model.FileData;
import org.cdb.filesystem.model.enums.FileType;
import org.cdb.filesystem.repository.FilesDataRepository;
import org.cdb.filesystem.repository.FilesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FilesServiceImpl implements FilesService
{
    private final FilesRepository filesRepository;
    private final FilesDataRepository filesDataRepository;

    public FilesServiceImpl(FilesRepository aFilesRepository, FilesDataRepository filesDataRepository)
    {
        this.filesRepository = aFilesRepository;
        this.filesDataRepository = filesDataRepository;
    }

    @Override
    public ApiFile addFile(ApiFileAddRequest fileAddRequest)
    {
        File file = new File(fileAddRequest); // Assuming constructor sets fileName and fileType
        file.setFileSize(countBase64Size(fileAddRequest.getData()));
        file.setOwner(fileAddRequest.getOwner());


        file = filesRepository.save(file);

        FileData fileData = new FileData();
        fileData.setFileMetadata(file);
        fileData.setFileBlob(fileAddRequest.getData());
        filesDataRepository.save(fileData);

        return convertToApiFile(file);
    }

    @Override
    public ApiFile getFileById(Long aFileId)
    {
        File file = filesRepository.findById(aFileId)
                .orElseThrow(() -> new FileNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ErrorEnum.FILE_NOT_FOUND, LocalDateTime.now()));
        return convertToApiFile(file);
    }

    @Override
    public ApiFullFile getFileDetailsById(Long aFileId)
    {
        FileData fileData = filesDataRepository.findById(aFileId)
                .orElseThrow(() -> new FileNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ErrorEnum.FILE_NOT_FOUND, LocalDateTime.now()));
        return convertToApiFullFile(fileData);
    }

    @Override
    public List<ApiFile> listFiles(String owner, FileType fileType, String filename, OrderEnum orderByDate)
    {
        List<File> files = new ArrayList<>();

        if (orderByDate == OrderEnum.DESC)
        {
            files = filesRepository.findByOwnerAndFileTypeAndFileNameOrderByCreateDateDsc(owner, fileType, filename);
        }
        else
        {
            files = filesRepository.findByOwnerAndFileTypeAndFileNameOrderByCreateDateAsc(owner, fileType, filename);
        }

        return files.stream().map(this::convertToApiFile).collect(Collectors.toList());
    }

    @Override
    public void deleteFile(Long fileId)
    {
        filesRepository.updateDeleteDateById(fileId, new Date())
                .orElseThrow(() -> new FileNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ErrorEnum.FILE_NOT_FOUND, LocalDateTime.now()));
    }

    private ApiFile convertToApiFile(File file)
    {
        ApiFile apiFile = new ApiFile();
        apiFile.setId(file.getId());
        apiFile.setFileName(file.getFileName());
        apiFile.setFileType(file.getFileType().getMimeType());
        apiFile.setFileSize(file.getFileSize());
        apiFile.setOwner(file.getOwner());
        apiFile.setCreationDate(file.getCreateDate());
        apiFile.setUpdateDate(file.getUpdateDate());
        apiFile.setDeleteDate(file.getDeleteDate());
        return apiFile;
    }

    private ApiFullFile convertToApiFullFile(FileData aFileData)
    {
        ApiFullFile apiFullFile = new ApiFullFile();
        apiFullFile.setData(aFileData.getFileBlob());

        File fileMetadata = aFileData.getFileMetadata();
        apiFullFile.setId(fileMetadata.getId());
        apiFullFile.setFileName(fileMetadata.getFileName());
        apiFullFile.setFileType(fileMetadata.getFileType().getMimeType());
        apiFullFile.setFileSize(fileMetadata.getFileSize());
        apiFullFile.setOwner(fileMetadata.getOwner());
        apiFullFile.setCreationDate(fileMetadata.getCreateDate());
        apiFullFile.setUpdateDate(fileMetadata.getUpdateDate());
        apiFullFile.setDeleteDate(fileMetadata.getDeleteDate());


        return apiFullFile;
    }

    private int countBase64Size(String in)
    {
        int count = 0;
        int pad = 0;
        for (int i = 0; i < in.length(); i++)
        {
            char c = in.charAt(i);
            if (c == '=')
            {
                pad++;
            }
            if (!Character.isWhitespace(c))
            {
                count++;
            }
        }
        return (count * 3 / 4) - pad;
    }
}

