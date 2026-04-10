package ru.itis.semestrwork_spring.services;

import java.util.Map;

public interface EmailService {
    public void sendEmail(String to, String subject, String templateName, Map<String, Object> model);
}
