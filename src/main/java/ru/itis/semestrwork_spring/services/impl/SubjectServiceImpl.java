package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.dto.SubjectForm;
import ru.itis.semestrwork_spring.models.Subject;
import ru.itis.semestrwork_spring.repositories.SubjectRepository;
import ru.itis.semestrwork_spring.services.SubjectService;

@Component
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void addSubject(SubjectForm subjectForm) {
        Subject subject = Subject.builder()
                .subjectName(subjectForm.getSubjectName())
                .description(subjectForm.getDescription())
                .difficulty(subjectForm.getDifficulty())
                .build();
        subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(String subjectName, String difficulty) {
        Subject subject = subjectRepository.getSubjectBySubjectName(subjectName);
        subject.setDifficulty(difficulty);
        subjectRepository.save(subject);
    }


}
