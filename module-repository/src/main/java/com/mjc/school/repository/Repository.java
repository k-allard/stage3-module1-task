package com.mjc.school.repository;

import com.mjc.school.common.exceptions.AuthorNotFoundException;
import com.mjc.school.common.exceptions.NewsNotFoundException;

import java.util.List;

public interface Repository<T> {

    List<T> readAll();

    T readById(Long id) throws NewsNotFoundException;

    T create(T data) throws AuthorNotFoundException;

    T update(T data) throws NewsNotFoundException, AuthorNotFoundException;

    boolean deleteById(Long id) throws NewsNotFoundException;

    int getNextId();
}