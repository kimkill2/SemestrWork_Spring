package ru.itis.semestrwork_spring.services;


import java.util.Map;

public interface ViewService {
    void registerView(Long teacherId, Long studentId);
    Map<Long, Long> getAllViews();
    void cleanUpOldViews();

}
