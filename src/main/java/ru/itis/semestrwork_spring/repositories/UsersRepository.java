package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.semestrwork_spring.dto.TeacherForm;
import ru.itis.semestrwork_spring.models.File;
import ru.itis.semestrwork_spring.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u, tp FROM User u LEFT JOIN u.teacherProfile tp WHERE u.role = 'TEACHER'")
    List<Object[]> findTeachers();

    @Query("SELECT u.avatar FROM User u WHERE u.username = :username")
    Optional<File> findFileByUsername(@Param("username") String username);



}
