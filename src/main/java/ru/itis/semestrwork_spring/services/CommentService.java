package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.dto.CommentDto;

public interface CommentService {
    public void addComment(CommentDto commentDto);

    void updateComment(Long id, String comment);
}
