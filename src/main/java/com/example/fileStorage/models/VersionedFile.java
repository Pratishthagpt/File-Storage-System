package com.example.fileStorage.models;

public class VersionedFile extends BaseModel{

    private String contentType;
    private String url;
    private Long size;
    private Long version;

    private File file;
}
