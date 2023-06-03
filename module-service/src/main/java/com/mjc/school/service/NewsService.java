package com.mjc.school.service;

import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsService {
    List<NewsDTOResponse> getAllNews();

    NewsDTOResponse getNewsById(Long id) throws NewsNotFoundException;

    NewsDTOResponse createNews(NewsCreateDTORequest news) throws NewsTitleInvalidException, NewsContentInvalidException;

    NewsDTOResponse updateNews(NewsUpdateDTORequest news) throws NewsNotFoundException, AuthorNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    boolean removeNews(Long id) throws NewsNotFoundException;
}
