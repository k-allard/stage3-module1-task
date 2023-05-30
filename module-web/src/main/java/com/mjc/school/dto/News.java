package com.mjc.school.dto;

import java.time.LocalDateTime;

public class News {
    Long id;
    String title;
    String content;
    LocalDateTime createDate;
    LocalDateTime lastUpdateDate;
    Long authorId;
}
