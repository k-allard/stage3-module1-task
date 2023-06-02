package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsNotFoundException;
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
    public NewsDTOResponse getNewsById(Long id) throws NewsNotFoundException {
        return newsService.getNewsById(id);
    }

    @Override
    public NewsDTOResponse createNews(NewsCreateDTORequest news) {
        return newsService.createNews(news);
    }

    @Override
    public NewsDTOResponse updateNews(NewsUpdateDTORequest news)
            throws AuthorNotFoundException, NewsNotFoundException {
        return newsService.updateNews(news);
    }

    @Override
    public boolean removeNews(Long id) throws NewsNotFoundException {
        return newsService.removeNews(id);
    }
}