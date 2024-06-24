package com.example.fileStorage.repositories;

import com.example.fileStorage.models.File;
import com.example.fileStorage.models.VersionedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionedFileRepository extends JpaRepository<VersionedFile, Long> {

    List<VersionedFile> findByFile(File file);

}
