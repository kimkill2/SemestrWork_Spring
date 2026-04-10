package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherForm {
    private String username;
    private String password;
    private String email;
    private String role;
    private String birthDate;
    private String gender;
    private String firstName;
    private String lastName;
    private String description;
    private Integer yearsOfExperience;
    private String specialization;
    private Double rating;
    private Integer pricePerHour;
}
