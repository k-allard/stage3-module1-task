package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;

import java.util.List;

public interface NewsController {

    List<NewsDTOResponse> getAllNews();

    NewsDTOResponse getNewsById(Long id);

    NewsDTOResponse createNews(NewsCreateDTORequest news);

    NewsDTOResponse updateNews(NewsUpdateDTORequest news);

    boolean removeNews(Long id);

}
