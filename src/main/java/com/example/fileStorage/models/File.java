package com.example.fileStorage.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class File extends BaseModel{

    private String fileName;
    private String contentType;
    private String url;
    private Long size;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "file_shared_users",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> sharedFilesWith;

}
