package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NewsRepositoryImpl implements NewsRepository {

    private static final AtomicInteger idSequence = new AtomicInteger(0);

    @Override
    public List<News> getNewsList() {
        return DataSource.getInstance().getNewsList();
    }

    @Override
    public List<Author> getAuthorList() {
        return DataSource.getInstance().getAuthorList();
    }

    @Override
    public int getNextNewsId() {
        return idSequence.incrementAndGet();
    }
}
