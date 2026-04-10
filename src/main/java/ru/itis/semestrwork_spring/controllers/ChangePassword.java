package ru.itis.semestrwork_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.semestrwork_spring.services.UsersService;

@Controller
public class ChangePassword {

    @Autowired
    private UsersService usersService;

    @GetMapping("/changePassword")
    public String changePassword() {
        return "change_password";
    }

    @PostMapping("/changePassword")
    public String processChangePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("password") String password, @RequestParam("repeatedPassword") String repeatedPassword) {
        System.out.println("Пароли проверяются");
        String username = "123";

        if (!repeatedPassword.equals(password)) {
            System.out.println("Passwords do not match");
            return "change_password";
        }
        if (password.length() < 6 || repeatedPassword.length() < 6) {
            System.out.println("Password should be at least 6 characters");
            return "change_password";
        }
        System.out.println("Пароли проверены");
        usersService.updatePassword(username, password);
        System.out.println("Данные сохранены");
        return "redirect:/signIn";
    }
}
