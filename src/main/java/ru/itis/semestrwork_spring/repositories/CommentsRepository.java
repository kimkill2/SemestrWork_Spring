package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.semestrwork_spring.dto.CommentsDto;
import ru.itis.semestrwork_spring.models.Comment;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTeacherUserId(Long id);

    @Query("""
    SELECT new ru.itis.semestrwork_spring.dto.CommentsDto(
        u.firstName,
        u.lastName,
        c.rating,
        c.createdTime,
        c.comment)
    FROM Comment c
    JOIN c.student s
    JOIN s.user u
    WHERE c.teacher.userId = :id
""")
    List<CommentsDto> findCommentsByTeacherId(@Param("id") Long id);}
