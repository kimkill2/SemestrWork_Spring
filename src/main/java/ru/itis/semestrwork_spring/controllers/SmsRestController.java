package ru.itis.semestrwork_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.semestrwork_spring.services.SmsService;
import ru.itis.semestrwork_spring.services.UsersService;

@RestController
@RequestMapping("/api/sms")
public class SmsRestController {
    @Autowired
    private SmsService smsService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Sending code to user: " + userDetails.getUsername());

        boolean isSent = smsService.sendAndStoreCode(usersService.getPhoneNumber(userDetails.getUsername()));
        System.out.println("Code sent: " + isSent);
        if (isSent) {
            return ResponseEntity.ok().body("Код отправлен");
        } else {
            System.out.println("Ошибка!");
            return ResponseEntity.internalServerError().body("Не удалось отправить смс");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestParam("code") String code) {
        if (smsService.verifySms(usersService.getPhoneNumber(userDetails.getUsername()), code)) {
            return ResponseEntity.ok().body("Код верный");
        } else {
            return ResponseEntity.badRequest().body("Неверный или просроченный код");
        }
    }
}
