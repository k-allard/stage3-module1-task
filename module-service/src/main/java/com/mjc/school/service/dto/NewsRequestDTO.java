package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public class NewsRequestDTO {
    Long id;
    String title;
    String content;
    LocalDateTime createDate;
    LocalDateTime lastUpdateDate;
    Long authorId;
}
