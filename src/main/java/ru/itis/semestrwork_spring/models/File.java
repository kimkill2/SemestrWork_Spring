package ru.itis.semestrwork_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String originalFileName;
    private String storageFileName;
    private String contentType;
    private Long size;
    private String url;

    @OneToMany(mappedBy = "avatar")
    private List<User> users = new ArrayList<>();
}
