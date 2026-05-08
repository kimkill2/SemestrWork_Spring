package ru.itis.semestrwork_spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.models.Logs;
import ru.itis.semestrwork_spring.repositories.LogRespository;
import ru.itis.semestrwork_spring.services.LogService;

import java.time.LocalDateTime;

@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRespository logRespository;

    @Override
    public void addLog(String text, String type, String className, String methodName) {
        Logs log = Logs.builder()
                .text(text)
                .type(type)
                .className(className)
                .methodName(methodName)
                .createdTime(LocalDateTime.now())
                .build();

        logRespository.save(log);
    }
}
