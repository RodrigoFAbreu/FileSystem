package org.cdb.filesystem.service;

import lombok.extern.log4j.Log4j2;
import org.cdb.filesystem.dao.file.ApiFile;
import org.cdb.filesystem.dao.file.ApiFileAddRequest;
import org.cdb.filesystem.dao.file.ApiFileUpdateRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    @Transactional
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
    public ApiFile getFile(Long aFileId)
    {
        File file = filesRepository.findById(aFileId)
                .orElseThrow(() -> new FileNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ErrorEnum.FILE_NOT_FOUND, LocalDateTime.now()));
        return convertToApiFile(file);
    }

    @Override
    public ApiFullFile getFileDetails(Long aFileId)
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
    @Transactional
    public ApiFullFile updateFile(Long fileId, ApiFileUpdateRequest updateRequest)
    {
        // Find the existing file
        File file = filesRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ErrorEnum.FILE_NOT_FOUND, LocalDateTime.now()));

        // Update file metadata
        file.setFileName(updateRequest.getFileName());
        file.setFileType(FileType.fromString(updateRequest.getFileType()));
        file.setOwner(updateRequest.getOwner());
        file.setUpdateDate(new Date());

        // Assuming fileSize is calculated based on the data string length
        file.setFileSize(updateRequest.getData().length());

        // Save the updated file metadata
        file = filesRepository.save(file);

        // Update the file data
        Optional<FileData> fileDataOptional = filesDataRepository.findByFileMetadata_Id(fileId);
        FileData fileData = fileDataOptional.orElse(new FileData());
        fileData.setFileMetadata(file);
        fileData.setFileBlob(updateRequest.getData());

        // Save the updated file data
        filesDataRepository.save(fileData);

        return convertToApiFullFile(fileData);
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

