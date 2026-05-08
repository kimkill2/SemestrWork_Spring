package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.semestrwork_spring.models.File;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByStorageFileName(String fileName);


}
