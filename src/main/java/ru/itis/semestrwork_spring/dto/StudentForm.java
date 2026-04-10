package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentForm {
    private String username;
    private String password;
    private String email;
    private String role;
    private String birthDate;
    private String gender;
    private String firstName;
    private String lastName;
    private String educationalInstitution;
    private String parentsFirstName;
    private String parentsLastName;
    private String phoneNumber;
}
