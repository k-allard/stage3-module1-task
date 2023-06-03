package com.mjc.school.repository;


import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataSource {

    private static DataSource instance;

    @Getter
    private final List<News> newsList;

    @Getter
    private final List<Author> authorList;

    private DataSource() {
        DataInitializer dataInitializer = new DataInitializer();
        authorList = dataInitializer.initializeAuthorList();
        newsList = dataInitializer.initializeNewsList(authorList);
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
}
