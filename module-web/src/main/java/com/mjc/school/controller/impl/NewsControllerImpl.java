package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.NewsServiceImpl;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;


import java.util.List;

public class NewsControllerImpl implements NewsController {

    private final NewsService newsService = new NewsServiceImpl();

    @Override
    public List<NewsDTOResponse> getAllNews() {
        return newsService.getAllNews();
    }

    @Override
    public NewsDTOResponse getNewsById(Long id) {
        return newsService.getNewsById(id);
    }

    @Override
    public NewsDTOResponse createNews(NewsCreateDTORequest news) {
        return newsService.createNews(news);
    }

    @Override
    public NewsDTOResponse updateNews(NewsUpdateDTORequest news) {
        return newsService.updateNews(news);
    }

    @Override
    public boolean removeNews(Long id) {
        return newsService.removeNews(id);
    }
}