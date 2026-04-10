package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.semestrwork_spring.models.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject getSubjectBySubjectName(String subjectName);
}
