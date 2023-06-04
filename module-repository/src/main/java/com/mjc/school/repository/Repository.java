package com.mjc.school.repository;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;

import java.util.List;

public interface Repository<T> {

    List<T> readAll();

    T readById(Long id) throws NewsNotFoundException;

    T create(T data) throws AuthorNotFoundException;

    T update(T data) throws NewsNotFoundException, AuthorNotFoundException;

    Boolean deleteById(Long id) throws NewsNotFoundException;

    int getNextId();
}