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
@Table(name = "teacher_profile")
public class TeacherProfile {
    @Id
    @Column(name = "teacher_id")
    private Long userId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private Integer yearsOfExperience;
    private Double rating;
    private Integer pricePerHour;
    private String description;
    private String specialization;

    @OneToMany(mappedBy = "teacher")
    private List<Comment> comments;

    @OneToMany(mappedBy = "teacher")
    private List<TeacherSubject> subjects = new ArrayList<>();

}
