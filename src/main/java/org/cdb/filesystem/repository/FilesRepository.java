package org.cdb.filesystem.repository;

import org.cdb.filesystem.model.File;
import org.cdb.filesystem.model.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilesRepository extends JpaRepository<File, Long>
{
    @Query("select file from File file where (:owner is null or file.owner = :owner) and (:fileType is null or file.fileType = :fileType) and (:fileName is null or file.fileName = :fileName)")
    List<File> findByOwnerAndFileTypeAndFileNameOrderByCreateDateAsc(String owner, FileType fileType, String fileName);

    @Query("select file from File file where (:owner is null or file.owner = :owner) and (:fileType is null or file.fileType = :fileType) and (:fileName is null or file.fileName = :fileName)")
    List<File> findByOwnerAndFileTypeAndFileNameOrderByCreateDateDsc(String owner, FileType fileType, String fileName);
}