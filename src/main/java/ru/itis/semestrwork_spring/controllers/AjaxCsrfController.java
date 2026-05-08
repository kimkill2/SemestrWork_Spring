package ru.itis.semestrwork_spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.semestrwork_spring.dto.*;
import ru.itis.semestrwork_spring.services.SignUpService;
import ru.itis.semestrwork_spring.services.UsersService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AjaxCsrfController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/api/signIn")
    public ResponseEntity<?> signInAjax(@RequestBody SignInAjaxDto dto, HttpSession session) {
        System.out.println("Login attempt for user: " + dto.getUsername());

        boolean userExists = usersService.userExists(dto.getUsername(), dto.getPassword());

        if (userExists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).body("Пользователь не существует");
        }

    }

    @PostMapping("/api/signUp")
    public ResponseEntity<?> signUpAjax(@RequestBody SignUpDto dto) {
        System.out.println("Before");
        boolean isExists = usersService.userExistsByUsername(dto.getUsername());
        System.out.println(isExists);
        System.out.println("After");
        if (!isExists) {
            if (dto.getRole().equals("Ученик")) {
                StudentForm studentForm = StudentForm.builder()
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .role(dto.getRole())
                        .email(dto.getEmail())
                        .birthDate(dto.getBirthDate())
                        .gender(dto.getGender())
                        .educationalInstitution(dto.getEducationalInstitution())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .parentsFirstName(dto.getParentsFirstName())
                        .parentsLastName(dto.getParentsLastName())
                        .phoneNumber(String.valueOf(dto.getPhoneNumber()))
                        .build();
                signUpService.addStudent(studentForm);
                return ResponseEntity.ok().build();
            } else if (dto.getRole().equals("Учитель")) {
                TeacherForm teacherForm = TeacherForm.builder()
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .role(dto.getRole())
                        .email(dto.getEmail())
                        .birthDate(dto.getBirthDate())
                        .gender(dto.getGender())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .description(dto.getDescription())
                        .pricePerHour(dto.getPricePerHour())
                        .specialization(dto.getSpecialization())
                        .yearsOfExperience(dto.getYearsOfExperience())
                        .build();
                signUpService.addTeacher(teacherForm);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("Данная роль недоступна");
            }



        } else {
            return ResponseEntity.status(401).body("Пользователь с таким именем уже существует");
        }
    }
}
