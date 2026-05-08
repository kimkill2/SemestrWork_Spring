package ru.itis.semestrwork_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestrwork_spring.dto.*;
import ru.itis.semestrwork_spring.services.*;

@Controller
public class AdminController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TeacherProfileService teacherProfileService;

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin_page";
    }


    @GetMapping("/adminPage/addAdmin")
    public String getAddAdminPage() {
        return "admin";
    }

    @PostMapping("/adminPage/addAdmin")
    public String adminPage(UserForm userForm) {
        signUpService.addUser(userForm);
        return "redirect:/admin";
    }

    @PostMapping("/adminPAge/deleteAdmin")
    public String deleteAdmin(UserForm userForm) {

        return "redirect:/admin";
    }

    @GetMapping("/adminPage/addStudent")
    public String getAddStudentPage() {
        return "student";
    }

    @PostMapping("/adminPage/addStudent")
    public String addStudent(StudentForm studentForm) {
        signUpService.addStudent(studentForm);
        return "redirect:/admin";
    }

    @GetMapping("/adminPage/addTeacher")
    public String getAddTeacherPage() {
        return "teacher";
    }

    @PostMapping("/adminPage/addTeacher")
    public String addTeacher(TeacherForm teacherForm) {
        signUpService.addTeacher(teacherForm);
        return "redirect:/admin";
    }

    @GetMapping("/adminPage/addComment")
    public String getAddCommentPage() {
        return "comment";
    }

    @PostMapping("/adminPage/addComment")
    public String addComment(CommentDto commentDto) {
        commentService.addComment(commentDto);
        return "redirect:/admin";
    }

    @GetMapping("/adminPage/addFile")
    public String getAddFilePage() {
        return "file";
    }

    @PostMapping("/adminPage/addFile")
    public String addFile(@RequestParam("file") MultipartFile file) {
        System.out.println("AdminController addFile");
        String filePath = fileService.addFile(file);
        System.out.println(filePath);;
        return "redirect:/admin";
    }

    @GetMapping("/adminPage/addSubject")
    public String getAddSubjectPage() {
        return "subject";
    }

    @PostMapping("/adminPage/addSubject")
    public String addSubject(SubjectForm subjectForm) {
        subjectService.addSubject(subjectForm);
        return "redirect:/admin";
    }

    @PostMapping("/adminPage/updatePassword")
    public String updatePassword(UserForm userForm) {
        System.out.println("AdminController updatePassword");
        usersService.updatePassword(userForm.getUsername(), userForm.getPassword());
        return "redirect:/admin";
    }

    @PostMapping("/adminPage/updateSpecialization")
    public String updateSpecialization(@RequestParam("username") String username, @RequestParam("specialization") String specialization) {
        teacherProfileService.updateTeacherSpecialization(username, specialization);
        return "redirect:/admin";
    }

    @PostMapping("/adminPage/updateSubjectDifficulty")
    public String updateSubjectDifficulty(@RequestParam("subjectName") String subjectName, @RequestParam("difficulty") String difficulty) {
        subjectService.updateSubject(subjectName, difficulty);
        return "redirect:/admin";
    }

    @PostMapping("/adminPage/updateParentsFirstName")
    public String updateParentsFirstName(@RequestParam("username") String username, @RequestParam("parentsFirstName") String firstName) {
        studentProfileService.updateStudentProfile(username, firstName);
        return "redirect:/admin";
    }



    @PostMapping("/adminPage/updateFileName")
    public String updateFile(@RequestParam("id") Long id, @RequestParam("fileName") String fileName) {
        fileService.updateFile(id, fileName);
        return "redirect:/admin";
    }

    @PostMapping("/adminPage/updateComment")
    public String updateComment(@RequestParam("id") Long id, @RequestParam("comment") String comment) {
        commentService.updateComment(id, comment);
        return "redirect:/admin";
    }



}
