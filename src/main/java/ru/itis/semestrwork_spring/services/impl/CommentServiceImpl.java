package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.dto.CommentsDto;
import ru.itis.semestrwork_spring.dto.CommentDto;
import ru.itis.semestrwork_spring.models.Comment;
import ru.itis.semestrwork_spring.models.StudentProfile;
import ru.itis.semestrwork_spring.models.TeacherProfile;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.CommentsRepository;
import ru.itis.semestrwork_spring.repositories.StudentProfileRepository;
import ru.itis.semestrwork_spring.repositories.TeacherProfileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.CommentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Override
    public void addComment(CommentDto commentDto) {
        String studentUsername = commentDto.getAuthor();
        String teacherUsername = commentDto.getSubject();

        Optional<User> student = usersRepository.findByUsername(studentUsername);
        Optional<User> teacher = usersRepository.findByUsername(teacherUsername);

        System.out.println(student.get());
        System.out.println(teacher.get());

        StudentProfile studentProfile = studentProfileRepository
                .findByUser(student.get())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        TeacherProfile teacherProfile = teacherProfileRepository
                .findByUser(teacher.get())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        System.out.println(LocalDate.now());
        Comment comment = Comment.builder()
                .student(studentProfile)
                .teacher(teacherProfile)
                .comment(commentDto.getText())
                .createdTime(LocalDateTime.now())
                .rating(commentDto.getRating())
                .build();
        commentsRepository.save(comment);
    }

    @Override
    public void updateComment(Long id, String commentText) {
        Comment comment = commentsRepository.findById(id).get();
        comment.setComment(commentText);
        commentsRepository.save(comment);
    }

    @Override
    public Map<String, Double> getAverageRating() {
        System.out.println("getAverageRating");
        List<Object[]> teachers = usersRepository.findTeachers();
        Map<String, Double> ratings = new HashMap<>();
        for (Object[] info : teachers) {
            User user = (User) info[0];
            List<Comment> commentsList = commentsRepository.findByTeacherUserId(user.getUserId());
            double averageRating = 0.0;
            if (!commentsList.isEmpty()) {
                for (Comment comment : commentsList) {
                    averageRating += comment.getRating();
                }
                averageRating = averageRating / commentsList.size();
                System.out.println(user.getUserId() + "|" + averageRating / commentsList.size());
            } else {
                averageRating = 0.0;
            }
            ratings.put(String.valueOf(user.getUserId()), averageRating);


        }
        return ratings;
    }

    @Override
    public List<CommentsDto> getComments(Long id) {
        System.out.println("CommentService.getComments");
        List<CommentsDto> comments = commentsRepository.findCommentsByTeacherId(id);
        return comments;
    }


}
