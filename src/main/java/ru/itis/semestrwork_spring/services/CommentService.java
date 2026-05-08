package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.dto.CommentDto;
import ru.itis.semestrwork_spring.dto.CommentsDto;
import ru.itis.semestrwork_spring.models.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void addComment(CommentDto commentDto);

    void updateComment(Long id, String comment);

    Map<String, Double> getAverageRating();

    List<CommentsDto> getComments(Long id);
}
