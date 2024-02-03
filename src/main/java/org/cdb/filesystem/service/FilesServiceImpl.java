package org.cdb.filesystem.service;

import org.cdb.filesystem.dto.file.ApiFile;
import org.cdb.filesystem.dto.file.ApiFileAddRequest;
import org.cdb.filesystem.model.File;
import org.cdb.filesystem.model.FileData;
import org.cdb.filesystem.repository.FilesDataRepository;
import org.cdb.filesystem.repository.FilesRepository;
import org.springframework.stereotype.Service;

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
        file.setOwner("test");


        file = filesRepository.save(file);

        FileData fileData = new FileData();
        fileData.setFileMetadata(file);
        fileData.setFileBlob(fileAddRequest.getData());
        filesDataRepository.save(fileData);

        return convertFileToApiFile(file);
    }

    private ApiFile convertFileToApiFile(File file)
    {
        ApiFile apiFile = new ApiFile();
        apiFile.setId(file.getId());
        apiFile.setFileName(file.getFileName());
        apiFile.setFileType(file.getFileType().getMimeType());
        apiFile.setFileSize(Math.toIntExact(file.getFileSize()));
        apiFile.setCreationDate(file.getCreateDate());
        apiFile.setUpdateDate(file.getUpdateDate());
        apiFile.setDeleteDate(file.getDeleteDate());
        return apiFile;
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

