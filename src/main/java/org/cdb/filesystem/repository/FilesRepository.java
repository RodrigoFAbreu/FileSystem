package org.cdb.filesystem.repository;

import org.cdb.filesystem.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<File, Long>
{
}