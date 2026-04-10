//package ru.itis.semestrwork_spring.exceptions;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public String handle403(Exception e, Model model) {
//        model.addAttribute("error", e.getMessage());
//        return "error_403";
//    }
//
//
//    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
//    public String handle404(Exception e, Model model) {
//        model.addAttribute("error", e.getMessage());
//        return "error_404";
//    }
//
//    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
//    public String handle405(Exception e, Model model) {
//        model.addAttribute("error", e.getMessage());
//        return "error_405";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handle500(Exception e, Model model) {
//        model.addAttribute("error", e.getMessage());
//        return "error_500";
//    }
//}