package org.cdb.filesystem.repository;

import org.cdb.filesystem.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilesDataRepository extends JpaRepository<FileData, Long>
{
    Optional<FileData> findByFileMetadata_Id(Long fileId);
}