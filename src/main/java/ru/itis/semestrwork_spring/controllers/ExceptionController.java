package ru.itis.semestrwork_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @ExceptionHandler(Exception.class)


    @GetMapping("/access-denied")
    public String error_403() {
        System.out.println("Access denied");
        return "error_403";
    }

    @GetMapping("/exceptions/error_404")
    public String error_404() {
        return "error_404";
    }

    @GetMapping("/exceptions/error_405")
    public String error_405() {
        return "error_405";
    }

    @GetMapping("/exceptions/error_500")
    public String error_500() {
        return "error_500";
    }


}
