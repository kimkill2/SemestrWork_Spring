package ru.itis.semestrwork_spring.services.impl;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.semestrwork_spring.dto.FileForm;
import ru.itis.semestrwork_spring.dto.UserAccountForm;
import ru.itis.semestrwork_spring.models.File;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.FileRepository;
import ru.itis.semestrwork_spring.repositories.UsersRepository;
import ru.itis.semestrwork_spring.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String addFile(MultipartFile uploadFile) {

        String storageFileName = UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();

        File file = File.builder()
                .originalFileName(uploadFile.getOriginalFilename())
                .storageFileName(uploadFile.getOriginalFilename())
                .size(uploadFile.getSize())
                .contentType(uploadFile.getContentType())
                .url(storagePath + "/" + storageFileName)
                .build();
        try {
            Files.copy(uploadFile.getInputStream(), Path.of(storagePath, storageFileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        fileRepository.save(file);
        return storageFileName;
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {

    }

    @Override
    public void updateFile(Long id, String fileName) {
        File file = fileRepository.findById(id).get();
        file.setOriginalFileName(fileName);
        fileRepository.save(file);
    }

    @Override
    public String getFilePath(String username) {
        Optional<File> file = usersRepository.findFileByUsername(username);
        System.out.println("If: " + file.isPresent());
        if (file.isPresent()) {
            System.out.println(file.get().getOriginalFileName());
            return file.get().getOriginalFileName();
        } else {
            return "common.jpg";
        }

    }

    @Override
    public String addFileToUser(UserAccountForm form) {
        User user = usersRepository.findByUsername(form.getUsername()).get();
        MultipartFile uploadFile = form.getFile();

        File oldAvatar = user.getAvatar();

        if (oldAvatar != null) {
            try {
                Path oldFilePAth = Path.of(storagePath, oldAvatar.getOriginalFileName());
                Files.deleteIfExists(oldFilePAth);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            fileRepository.delete(oldAvatar);
        }

        String storageFileName = UUID.randomUUID() + "_" + uploadFile.getOriginalFilename();

        File file = File.builder()
                .originalFileName(uploadFile.getOriginalFilename())
                .storageFileName(uploadFile.getOriginalFilename())
                .size(uploadFile.getSize())
                .contentType(uploadFile.getContentType())
                .url(storagePath + "/" + storageFileName)
                .build();
        try {
            Files.copy(uploadFile.getInputStream(), Path.of(storagePath, storageFileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileRepository.save(file);
        user.setAvatar(file);
        usersRepository.save(user);

        return storageFileName;
    }
}
