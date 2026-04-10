package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.models.StudentProfile;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.TeacherProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.TeacherProfileService;

@Component
public class TeacherProfileServiceImpl implements TeacherProfileService {

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void updateTeacherSpecialization(String username, String specialization) {
        User user = usersRepository.findByUsername(username).get();
        TeacherProfile teacherProfile = teacherProfileRepository.findByUser(user).get();
        teacherProfile.setSpecialization(specialization);
        teacherProfileRepository.save(teacherProfile);
    }
}
