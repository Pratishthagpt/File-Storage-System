package com.example.fileStorage.repositories;

import com.example.fileStorage.models.File;
import com.example.fileStorage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    public List<File> findByUser(User user);
}
