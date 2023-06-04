package com.mjc.school.controller;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsController {

    List<NewsDTO> getAllNews();

    NewsDTO getNewsById(Long id) throws NewsNotFoundException;

    NewsDTO createNews(NewsCreateDTORequest news) throws NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException;

    NewsDTO updateNews(NewsUpdateDTORequest news) throws AuthorNotFoundException, NewsNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    boolean removeNews(Long id) throws NewsNotFoundException;

}
