package ru.itis.semestrwork_spring.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itis.semestrwork_spring.models.View;
import ru.itis.semestrwork_spring.repositories.ViewRepository;
import ru.itis.semestrwork_spring.services.ViewService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewRepository viewRepository;

    @Override
    @Transactional
    public void registerView(Long teacherId, Long userId) {
        boolean alreadyViewed = viewRepository.existsTodayView(teacherId, userId);

        if (!alreadyViewed) {
            View view = View.builder()
                    .teacher_id(teacherId)
                    .user_id(userId)
                    .viewDate(LocalDateTime.now())
                    .build();
            viewRepository.save(view);
            System.out.println("Registered view");
        }

    }

    @Override
    public Map<Long, Long> getAllViews() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDateTime.now().plusDays(1);

        List<Object[]> views = viewRepository.getAllViews(startOfDay, endOfDay);
        System.out.println(views);
        return views.stream()
                .collect(Collectors.toMap(
                row -> (Long) row[0],
                row -> (Long) row[1]
        ));
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanUpOldViews() {
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        viewRepository.deleteTodayView(oneDayAgo);

    }
}
