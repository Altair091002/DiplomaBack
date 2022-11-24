package com.example.demoauth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data @NoArgsConstructor
public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private Instant createdOn;
    private Instant updatedOn;
}
