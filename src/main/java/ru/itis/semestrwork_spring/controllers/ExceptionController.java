package ru.itis.semestrwork_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/exceptions/error_403")
    public String error_403() {
        return "exceptions/error_403";
    }

    @GetMapping("/exceptions/error_404")
    public String error_404() {
        return "exceptions/error_404";
    }

    @GetMapping("/exceptions/error_405")
    public String error_405() {
        return "exceptions/error_405";
    }

    @GetMapping("/exceptions/error_500")
    public String error_500() {
        return "exceptions/error_500";
    }


}
