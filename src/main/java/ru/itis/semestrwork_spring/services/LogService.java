package ru.itis.semestrwork_spring.services;

public interface LogService {
    void addLog(String text, String type, String className, String methodName);
}
