package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentProfileDto {
    private String educationalInstitution;
    private String parentsFirstName;
    private String parentsLastName;
    private String phoneNumber;
}
