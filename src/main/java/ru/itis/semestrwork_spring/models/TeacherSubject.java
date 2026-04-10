package ru.itis.semestrwork_spring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers_subjects")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherProfile teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
