package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.semestrwork_spring.models.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
