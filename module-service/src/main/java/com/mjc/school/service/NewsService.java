package com.mjc.school.service;

import com.mjc.school.service.dto.NewsCreateDtoRequest;
import com.mjc.school.service.dto.NewsUpdateDtoRequest;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

import java.util.List;

public interface NewsService<T> {
    List<T> readAll();

    T readById(Long id) throws NewsNotFoundException;

    T create(NewsCreateDtoRequest news) throws NewsTitleInvalidException, NewsContentInvalidException, AuthorNotFoundException;

    T update(NewsUpdateDtoRequest news) throws NewsNotFoundException, AuthorNotFoundException, NewsTitleInvalidException, NewsContentInvalidException;

    Boolean delete(Long id) throws NewsNotFoundException;
}
