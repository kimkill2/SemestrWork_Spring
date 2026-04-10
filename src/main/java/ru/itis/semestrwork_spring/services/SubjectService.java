package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.dto.SubjectForm;

public interface SubjectService {
    void addSubject(SubjectForm subjectForm);

    void updateSubject(String subjectName, String difficulty);
}
