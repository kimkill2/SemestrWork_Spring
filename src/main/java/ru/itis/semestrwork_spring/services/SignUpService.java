package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.dto.StudentForm;
import ru.itis.semestrwork_spring.dto.TeacherForm;
import ru.itis.semestrwork_spring.dto.UserForm;

public interface SignUpService {
    void addUser(UserForm userForm);
    void addStudent(StudentForm studentForm);
    void addTeacher(TeacherForm teacherForm);
}
