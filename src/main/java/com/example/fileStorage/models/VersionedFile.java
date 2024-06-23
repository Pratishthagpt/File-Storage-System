package com.example.fileStorage.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VersionedFile extends BaseModel{

    private String contentType;
    private String url;
    private Long size;
    private Long version;

    @ManyToOne
    private File file;
}
