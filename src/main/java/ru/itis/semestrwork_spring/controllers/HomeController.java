package ru.itis.semestrwork_spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.semestrwork_spring.models.Role;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.UsersRepository;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/user")
    public String getHomePage(Principal principal, HttpSession session) {

        User user = usersRepository.findByUsername(principal.getName()).get();
        String roleName = user.getRole().getName();

        if ("Учитель".equals(roleName) || "Ученик".equals(roleName)) {
            session.setAttribute("role", roleName);
            return "redirect:/home";
        } else if ("Админ".equals(roleName)) {
            session.setAttribute("role", roleName);
            return "redirect:/admin";
        }
        System.out.println("No valid role found");
        return "redirect:/signIn";
    }


    @GetMapping("/user/teacher")
    public String getTeacherPage() {
        return "teacher_page";
    }

    @GetMapping("/user/student")
    public String getStudentPage() {
        return "student_page";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home_page";
    }

    @GetMapping("/user/home/show")
    public String showHomePage() {
        return "home_show";
    }

    @PostMapping("/user/home/show")
    public String showHomePage(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Model model) {
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        System.out.println(firstName + " " + lastName);
        return "home_show";
    }

    @GetMapping("/account")
    public String getAccountPage(Model model, HttpSession session) {
        model.addAttribute("role", session.getAttribute("role"));
        return "account_page";
    }

    @GetMapping("/teachers")
    public String getTeachersPage(Model model) {
        System.out.println(usersRepository.findTeachers().size());
        model.addAttribute("teachers", usersRepository.findTeachers());
        return "findTeachers";
    }



}
