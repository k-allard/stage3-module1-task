package com.mjc.school.controller.impl;

import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;


import java.util.List;

public class NewsControllerImpl implements NewsController {

    private final NewsService newsService = new NewsServiceImpl();

    @Override
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @Override
    public NewsDTO getNewsById(Long id) throws NewsNotFoundException {
        return newsService.getNewsById(id);
    }

    @Override
    public NewsDTO createNews(NewsCreateDTORequest news) throws
            NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException {
        return newsService.createNews(news);
    }

    @Override
    public NewsDTO updateNews(NewsUpdateDTORequest news) throws
            AuthorNotFoundException,
            NewsNotFoundException,
            NewsTitleInvalidException,
            NewsContentInvalidException {
        return newsService.updateNews(news);
    }

    @Override
    public boolean removeNews(Long id) throws NewsNotFoundException {
        return newsService.removeNews(id);
    }
}