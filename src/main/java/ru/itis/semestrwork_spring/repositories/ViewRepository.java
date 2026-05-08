package ru.itis.semestrwork_spring.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.semestrwork_spring.models.View;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    @Query("SELECT COUNT(v) > 0 FROM View v WHERE v.teacher_id = :teacherId AND v.user_id = :userId AND FUNCTION('DATE', v.viewDate) = CURRENT_DATE")
    Boolean existsTodayView(@Param("teacherId") Long teacherId, @Param("userId") Long userId);

    @Query("SELECT v.teacher_id, COUNT(DISTINCT v.user_id) FROM View v WHERE v.viewDate >= :startOfDay AND v.viewDate < :endOfDay GROUP BY v.teacher_id")
    List<Object[]> getAllViews(@Param("startOfDay") LocalDateTime startOfDay,
                               @Param("endOfDay") LocalDateTime endOfDay);
    @Modifying
    @Transactional
    @Query("DELETE FROM View v WHERE v.viewDate < :date")
    void deleteTodayView(@Param("date") LocalDateTime date);
}
