package ru.itis.semestrwork_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.semestrwork_spring.models.Logs;

@Repository
public interface LogRespository extends JpaRepository<Logs, Long> {

}
