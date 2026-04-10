package ru.itis.semestrwork_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semestrwork_spring.models.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String username;
    private String password;
    private String email;
    private String role;
    private String birthDate;
    private String gender;
    private String firstName;
    private String lastName;
}