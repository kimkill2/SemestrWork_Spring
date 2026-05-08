package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountForm {
    private MultipartFile file;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private Integer yearsOfExperience;
    private String specialization;
    private Integer pricePerHour;
}