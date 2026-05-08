package ru.itis.semestrwork_spring.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @ToString.Exclude
    private TeacherProfile teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private StudentProfile student;

    private Double rating;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private String comment;

}
