package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.dto.UserAccountForm;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.FileRepository;
import ru.itis.semestrwork_spring.repositories.TeacherProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.UsersService;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void updatePassword(String username, String password) {
        User user = usersRepository.findByUsername(username).get();
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        if (usersRepository.findByUsername(username).isPresent()) {
            usersRepository.deleteById(usersRepository.findByUsername(username).get().getUserId());

        } else {
            System.out.println("User not found");
        }
    }

    @Override
    public boolean userExists(String username, String password) {
        if (usersRepository.findByUsername(username).isPresent()) {
            return passwordEncoder.matches(password, usersRepository.findByUsername(username).get().getPassword());
        }
        return false;
    }

    @Override
    public void changeUserData(UserAccountForm form) {
        User user = usersRepository.findByUsername(form.getUsername()).get();
        TeacherProfile teacherProfile;
        Optional<TeacherProfile> teacher = teacherProfileRepository.findByUser(user);
        if (teacher.isEmpty()) {
            teacherProfile = new TeacherProfile();
            teacherProfile.setUser(user);
        } else {
            teacherProfile = teacher.get();
        }

        if (!form.getFirstName().equals(user.getFirstName())) {
            user.setFirstName(form.getFirstName());
        }
        if (!form.getLastName().equals(user.getLastName())) {
            user.setLastName(form.getLastName());
        }

        if (!form.getDescription().equals(teacherProfile.getDescription())) {
            teacherProfile.setDescription(form.getDescription());
        }

        if (!form.getYearsOfExperience().equals(teacherProfile.getYearsOfExperience())) {
            teacherProfile.setYearsOfExperience(form.getYearsOfExperience());
        }

        if (!form.getSpecialization().equals(teacherProfile.getSpecialization())) {
            teacherProfile.setSpecialization(form.getSpecialization());
        }

        if (!form.getPricePerHour().equals(teacherProfile.getPricePerHour())) {
            teacherProfile.setPricePerHour(form.getPricePerHour());
        }
        usersRepository.save(user);
        teacherProfileRepository.save(teacherProfile);
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }

    @Override
    public String getPhoneNumber(String username) {
        return usersRepository.findByUsername(username).get().getPhoneNumber();
    }
}
