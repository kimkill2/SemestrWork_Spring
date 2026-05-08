package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private String studentFirstName;
    private String studentLastName;
    private Double rating;
    private LocalDateTime createdTime;
    private String text;
}
