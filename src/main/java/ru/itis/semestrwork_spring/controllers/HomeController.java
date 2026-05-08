package ru.itis.semestrwork_spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.semestrwork_spring.dto.CommentDto;
import ru.itis.semestrwork_spring.dto.CommentsDto;
import ru.itis.semestrwork_spring.dto.UserAccountForm;
import ru.itis.semestrwork_spring.dto.UserForm;
import ru.itis.semestrwork_spring.models.Role;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.TeacherProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.security.CustomUserDetailsImpl;
import ru.itis.semestrwork_spring.services.CommentService;
import ru.itis.semestrwork_spring.services.UsersService;
import ru.itis.semestrwork_spring.services.ViewService;
import ru.itis.semestrwork_spring.services.impl.CommentServiceImpl;
import ru.itis.semestrwork_spring.services.impl.FileServiceImpl;
import ru.itis.semestrwork_spring.services.impl.SmsServiceImpl;

import java.io.File;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private SmsServiceImpl smsService;

    @Autowired
    private ViewService viewService;


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
    public String getTeacherPage(Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
        return "teacher_page";
    }

    @GetMapping("/user/student")
    public String getStudentPage(Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
        return "student_page";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        System.out.println("Имя: " + userDetails.getUser().getUsername());
        System.out.println("Роль: " + userDetails.getUser().getRole().getName());
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
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
    public String getAccountPage(Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        System.out.println("Role: " + userDetails.getUser().getRole());
        model.addAttribute("role", userDetails.getUser().getRole());
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
        return "account_page";
    }

    @GetMapping("/teachers")
    public String getTeachersPage(Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        List<Object[]> teachers = usersRepository.findTeachers();
        Map<String, Double> ratings = commentService.getAverageRating();
        Map<Long, Long> viewsMap = viewService.getAllViews();

        List<Map<String, Object>> teachersWithStats = new ArrayList<>();
        for (Object[] teacherProfile : teachers) {
            Map<String, Object> teacherStats = new HashMap<>();
            User teacher = (User) teacherProfile[0];
            teacherStats.put("user", teacher);
            teacherStats.put("rating", ratings.getOrDefault(teacher.getUserId(), 0.0));
            teacherStats.put("views", viewsMap.getOrDefault(teacher.getUserId(), 0L));
            teachersWithStats.add(teacherStats);
        }
        model.addAttribute("teachers", teachersWithStats);
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));

        return "findTeachers";
    }

    @GetMapping("/teachers/{userId}")
    public String getTeacherPage(@PathVariable("userId") Long userId, Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        User user = usersRepository.findById(userId).get();
        viewService.registerView(userId, userDetails.getUser().getUserId());

        List<CommentsDto> comments = commentService.getComments(userId);

        List<Map<String, Object>> formattedComments = comments.stream()
                .map(comment -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("studentFirstName", comment.getStudentFirstName());
                    map.put("studentLastName", comment.getStudentLastName());
                    map.put("text", comment.getText());
                    map.put("rating", comment.getRating());
                    map.put("formattedTime", comment.getCreatedTime()
                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                    return map;
                })
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("profile", teacherProfileRepository.findByUser(user).get());
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
        model.addAttribute("ratings", commentService.getAverageRating());
        model.addAttribute("comments", formattedComments);

        return "teacher_page";
    }

    @PostMapping("/teachers/{userId}")
    public String addComment(@PathVariable("userId") Long userId,@RequestParam("rating") Double rating ,@RequestParam("comment") String comment, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {
        User teacher = usersRepository.findById(userId).get();
        User student = usersRepository.findByUsername(userDetails.getUser().getUsername()).get();
        System.out.println("Рейтинг: " + rating);
        CommentDto commentDto = CommentDto.builder()
                .text(comment)
                .subject(teacher.getUsername())
                .author(student.getUsername())
                .rating(rating)
                .build();
        commentService.addComment(commentDto);
        return "redirect:/teachers/" + userId;
    }


    @PostMapping("/account/changeUserData")
    public String changeUserData(UserAccountForm form, Model model, @AuthenticationPrincipal CustomUserDetailsImpl userDetails) {

        model.addAttribute("role", userDetails.getUser().getRole());
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("avatarSrc", fileService.getFilePath(userDetails.getUser().getUsername()));
        System.out.println(form);
        usersService.changeUserData(form);
        fileService.addFileToUser(form);
        System.out.println("Changed user data");
        return "account_page";
    }

    @GetMapping("/sendCode")
    public String sendCode() {
        return "send_code";
    }



}
