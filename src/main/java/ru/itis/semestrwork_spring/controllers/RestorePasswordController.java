package ru.itis.semestrwork_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.EmailService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller
public class RestorePasswordController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsersRepository usersRepository;


    @GetMapping("/restore")
    public String restore() {
        System.out.println("GET controller");
        return "restore_password";
    }

    @PostMapping("/restorePassword")
    public String sendEmail(@RequestParam("email") String email) {
        System.out.println("Post controller");
        Optional<User> user = usersRepository.findByEmail(email);
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", user.get().getUsername());
        variables.put("link", "http://localhost:8080/changePassword");
        emailService.sendEmail(email, "ChangePassword", "email", variables);
        return "redirect:/signUp";
    }
}
