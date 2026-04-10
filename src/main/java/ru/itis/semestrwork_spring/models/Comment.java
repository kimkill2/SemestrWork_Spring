package ru.itis.semestrwork_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private TeacherProfile teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentProfile student;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private String comment;

}
