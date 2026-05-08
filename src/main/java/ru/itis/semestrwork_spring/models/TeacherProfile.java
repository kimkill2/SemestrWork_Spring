package ru.itis.semestrwork_spring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher_profile")
public class TeacherProfile {
    @Id
    @Column(name = "teacher_id")
    private Long userId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    private Integer yearsOfExperience;
    private Integer pricePerHour;
    private String description;
    private String specialization;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private List<TeacherSubject> subjects = new ArrayList<>();

}
