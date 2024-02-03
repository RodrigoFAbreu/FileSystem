package org.cdb.filesystem.repository;

import org.cdb.filesystem.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData, Long>
{
}