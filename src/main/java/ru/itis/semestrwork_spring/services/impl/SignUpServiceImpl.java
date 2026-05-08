package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.dto.StudentForm;
import ru.itis.semestrwork_spring.dto.TeacherForm;
import ru.itis.semestrwork_spring.dto.UserForm;
import ru.itis.semestrwork_spring.models.Role;
import ru.itis.semestrwork_spring.models.StudentProfile;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.StudentProfileRepository;
import ru.itis.semestrwork_spring.repositories.TeacherProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.LogService;
import ru.itis.semestrwork_spring.services.SignUpService;


@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
   public void addUser(UserForm userForm) {
        System.out.println(userForm.toString());
        try {
            User user = User.builder()
                    .username(userForm.getUsername())
                    .password(passwordEncoder.encode(userForm.getPassword()))
                    .confirmed("NON-CONFIRMED")
                    .email(userForm.getEmail())
                    .role(Role.getRole(userForm.getRole()))
                    .gender(userForm.getGender())
                    .birthDate(userForm.getBirthDate())
                    .firstName(userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .build();
            usersRepository.save(user);
            logService.addLog("Админ зарегистрирован", "info", "SignUpServiceImpl", "addUser");

        } catch (Exception e) {
            logService.addLog(e.getMessage(), "error", "SignUpServiceImpl", "addUser");
        }
    }

    @Override
    public void addStudent(StudentForm studentForm) {
        System.out.println(studentForm.toString());
        try {
            User user = User.builder()
                    .username(studentForm.getUsername())
                    .password(passwordEncoder.encode(studentForm.getPassword()))
                    .confirmed("NON-CONFIRMED")
                    .email(studentForm.getEmail())
                    .role(Role.getRole(studentForm.getRole()))
                    .gender(studentForm.getGender())
                    .birthDate(studentForm.getBirthDate())
                    .firstName(studentForm.getFirstName())
                    .lastName(studentForm.getLastName())
                    .phoneNumber(studentForm.getPhoneNumber())
                    .build();
            usersRepository.save(user);

            StudentProfile studentProfile = StudentProfile.builder()
                    .user(user)
                    .educationalInstitution(studentForm.getEducationalInstitution())
                    .parentsFirstName(studentForm.getParentsFirstName())
                    .parentsLastName(studentForm.getParentsLastName())
                    .build();
            studentProfileRepository.save(studentProfile);
            logService.addLog("Студент зарегистрирован", "info", "SignUpServiceImpl", "addStudent");
        } catch (Exception e) {
            logService.addLog(e.getMessage(), "error", "SignUpServiceImpl", "addStudent");
        }


    }

    @Override
    public void addTeacher(TeacherForm teacherForm) {
        System.out.println(teacherForm.toString());
        System.out.println("addTeacher:SignUpServiceImpl");
        try {
            User user = User.builder()
                    .username(teacherForm.getUsername())
                    .password(passwordEncoder.encode(teacherForm.getPassword()))
                    .confirmed("NON-CONFIRMED")
                    .email(teacherForm.getEmail())
                    .role(Role.getRole(teacherForm.getRole()))
                    .gender(teacherForm.getGender())
                    .birthDate(teacherForm.getBirthDate())
                    .firstName(teacherForm.getFirstName())
                    .lastName(teacherForm.getLastName())
                    .build();
            usersRepository.save(user);

            TeacherProfile teacherProfile = TeacherProfile.builder()
                    .user(user)
                    .description(teacherForm.getDescription())
                    .pricePerHour(teacherForm.getPricePerHour())
                    .specialization(teacherForm.getSpecialization())
                    .yearsOfExperience(teacherForm.getYearsOfExperience())
                    .build();
            teacherProfileRepository.save(teacherProfile);
            logService.addLog("Учитель зарегистрирован", "info", "SignUpServiceImpl", "addTeacher");
        } catch (Exception e) {
            logService.addLog(e.getMessage(), "error", "SignUpServiceImpl", "addTeacher");
        }

    }
}
