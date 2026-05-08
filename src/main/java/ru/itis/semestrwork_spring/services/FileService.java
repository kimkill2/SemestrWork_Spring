package ru.itis.semestrwork_spring.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestrwork_spring.dto.FileForm;
import ru.itis.semestrwork_spring.dto.UserAccountForm;
import ru.itis.semestrwork_spring.models.File;

public interface FileService {
    String addFile(MultipartFile uploadFile);
    void writeFileToResponse(String fileName, HttpServletResponse response);
    void updateFile(Long id, String fileName);
    String getFilePath(String username);
    String addFileToUser(UserAccountForm form);


}
