package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDate birthDate;
    private String gender;
    private String firstName;
    private String lastName;
    private String description;
    private Integer yearsOfExperience;
    private String specialization;
    private Integer pricePerHour;
    private String educationalInstitution;
    private String parentsFirstName;
    private String parentsLastName;
    private String phoneNumber;
}

