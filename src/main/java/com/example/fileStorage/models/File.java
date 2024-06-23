package com.example.fileStorage.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class File extends BaseModel{

    private String contentType;
    private String url;
    private Long size;

    @ManyToOne
    private User user;

}
