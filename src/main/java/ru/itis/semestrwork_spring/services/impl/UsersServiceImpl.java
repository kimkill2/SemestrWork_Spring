package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.UsersService;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void updatePassword(String username, String password) {
        User user = usersRepository.findByUsername(username).get();
        user.setPassword(passwordEncoder.encode(password));
        usersRepository.save(user);
    }
}
