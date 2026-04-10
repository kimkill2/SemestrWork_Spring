package ru.itis.semestrwork_spring.services.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.semestrwork_spring.services.EmailService;

import java.io.IOException;
import java.util.Map;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void sendEmail(String to, String subject, String templateName, Map<String, Object> model) {
        System.out.println("Отправка письма");
        try {
            Template template = freeMarkerConfig.getTemplate(templateName + ".ftlh");
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
            System.out.println("Письмо отправлено");
        } catch (MessagingException | IOException | TemplateException e) {
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
