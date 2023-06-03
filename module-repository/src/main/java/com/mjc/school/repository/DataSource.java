package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class DataSource {
    @Getter
    private final List<News> newsList;

    @Getter
    private final List<Author> authorList;

    private final AtomicInteger idSequence = new AtomicInteger(0);

    DataSource() {
        DataInitializer dataInitializer = new DataInitializer(idSequence);
        authorList = dataInitializer.initializeAuthorList();
        newsList = dataInitializer.initializeNewsList(authorList);
    }

    public int getNextNewsId() {
        return idSequence.incrementAndGet();
    }
}
