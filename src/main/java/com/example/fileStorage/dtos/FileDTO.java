package com.example.fileStorage.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {

    private Long id;
    private String filename;
    private String contentType;
    private String url;
    private Long size;
}
