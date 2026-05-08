package ru.itis.semestrwork_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.semestrwork_spring.services.CommentService;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{userId}")
    public String comments(@PathVariable Long userId, Model model) {
        System.out.println("Comment Controller");
        model.addAttribute("comments", commentService.getComments(userId));
        return "comments";

    }
}
