package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;

import java.util.Optional;

public interface TeacherProfileRepository extends CrudRepository<TeacherProfile, Integer> {
    Optional<TeacherProfile> findByUser(User user);
}
