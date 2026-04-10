package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.models.StudentProfile;

public interface StudentProfileService {
    void updateStudentProfile(String username, String parentsFirstName);
}
