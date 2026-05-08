package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semestrwork_spring.models.StudentProfile;
import ru.itis.semestrwork_spring.models.TeacherProfile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String text;
    private String author;
    private String subject;
    private Double rating;

}
