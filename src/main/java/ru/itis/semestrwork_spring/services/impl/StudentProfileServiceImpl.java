package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.models.StudentProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.StudentProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.StudentProfileService;

import java.util.Optional;

@Component
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public void updateStudentProfile(String username, String parentsFirstName) {
        Optional<User> user = usersRepository.findByUsername(username);
        StudentProfile studentProfile = studentProfileRepository.findByUser(user.get()).get();
        studentProfile.setParentsFirstName(parentsFirstName);
        studentProfileRepository.save(studentProfile);
    }
}
