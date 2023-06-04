package com.mjc.school.service;

import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsService {
    List<NewsDTO> readAll();

    NewsDTO readById(Long id) throws NewsNotFoundException;

    NewsDTO create(NewsCreateDTORequest news) throws NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException;

    NewsDTO update(NewsUpdateDTORequest news) throws NewsNotFoundException, AuthorNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    Boolean delete(Long id) throws NewsNotFoundException;
}
