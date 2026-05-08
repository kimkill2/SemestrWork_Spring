package ru.itis.semestrwork_spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String text;

    private String type;

    private String className;

    private String methodName;

    private LocalDateTime createdTime;

}
