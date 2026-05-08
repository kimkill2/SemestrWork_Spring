package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestrwork_spring.models.Role;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDate birthDate;
    private String gender;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}