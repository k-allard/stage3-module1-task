package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    private final DataSource dataSource = new DataSource();

    @Override
    public List<News> getNewsList() {
        return dataSource.getNewsList();
    }

    @Override
    public List<Author> getAuthorList() {
        return dataSource.getAuthorList();
    }

    public int getNextNewsId() {
        return dataSource.getNextNewsId();
    }
}
