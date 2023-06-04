package com.mjc.school.controller;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.dto.NewsCreateDtoRequest;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsController<T> {

    List<T> readAllNews();

    T readById(Long id) throws NewsNotFoundException;

    T createNews(NewsCreateDtoRequest news) throws NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException;

    T updateNews(NewsUpdateDTORequest news) throws AuthorNotFoundException, NewsNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    Boolean deleteNews(Long id) throws NewsNotFoundException;

}
