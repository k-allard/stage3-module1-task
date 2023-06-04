package com.mjc.school.service;

import com.mjc.school.common.dto.NewsCreateDTORequest;
import com.mjc.school.common.dto.NewsDTO;
import com.mjc.school.common.dto.NewsUpdateDTORequest;
import com.mjc.school.common.exceptions.AuthorNotFoundException;
import com.mjc.school.common.exceptions.NewsNotFoundException;
import com.mjc.school.common.exceptions.NewsContentInvalidException;
import com.mjc.school.common.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsService {
    List<NewsDTO> getAllNews();

    NewsDTO getNewsById(Long id) throws NewsNotFoundException;

    NewsDTO createNews(NewsCreateDTORequest news) throws NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException;

    NewsDTO updateNews(NewsUpdateDTORequest news) throws NewsNotFoundException, AuthorNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    boolean removeNews(Long id) throws NewsNotFoundException;
}
