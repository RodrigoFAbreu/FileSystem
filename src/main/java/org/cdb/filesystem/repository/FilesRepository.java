package org.cdb.filesystem.repository;

import org.cdb.filesystem.model.File;
import org.cdb.filesystem.model.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FilesRepository extends JpaRepository<File, Long>
{
    @Query("select file from File file where (:owner is null or file.owner = :owner) and (:fileType is null or file.fileType = :fileType) and (:fileName is null or file.fileName = :fileName) order by file.createDate asc")
    List<File> findByOwnerAndFileTypeAndFileNameOrderByCreateDateAsc(String owner, FileType fileType, String fileName);

    @Query("select file from File file where (:owner is null or file.owner = :owner) and (:fileType is null or file.fileType = :fileType) and (:fileName is null or file.fileName = :fileName) order by file.createDate desc")
    List<File> findByOwnerAndFileTypeAndFileNameOrderByCreateDateDsc(String owner, FileType fileType, String fileName);

    @Transactional
    @Modifying
    @Query("update File f set f.deleteDate = :deleteDate where f.id = :id")
    Optional<Integer> updateDeleteDateById(Long id, Date deleteDate);
}