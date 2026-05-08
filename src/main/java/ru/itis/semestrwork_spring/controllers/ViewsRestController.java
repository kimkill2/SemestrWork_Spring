package ru.itis.semestrwork_spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semestrwork_spring.services.ViewService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/views")
public class ViewsRestController {

    @Autowired
    private ViewService viewService;

    @GetMapping("/today")
    public ResponseEntity<Map<String, Long>> getAllViews() {
        Map<Long, Long> viewsMap = viewService.getAllViews();
        Map<String, Long> result = new HashMap<>();
        viewsMap.forEach((k, v) -> result.put(String.valueOf(k), v));

        return ResponseEntity.ok()
                .header("Cache-Control", "no-cache")
                .body(result);
    }
}
