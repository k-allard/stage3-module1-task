package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsRequestDTO;
import com.mjc.school.service.dto.NewsResponseDTO;

public interface NewsController {
    NewsResponseDTO createNews(NewsRequestDTO news);

}
