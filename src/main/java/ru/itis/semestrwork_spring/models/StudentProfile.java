package ru.itis.semestrwork_spring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @Column(name = "student_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String parentsFirstName;
    private String parentsLastName;
    private String educationalInstitution;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Comment> comments;




}
