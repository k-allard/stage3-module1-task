package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.util.List;

public interface NewsRepository {
    List<News> getNewsList();

    List<Author> getAuthorList();

    int getNextNewsId();
}
