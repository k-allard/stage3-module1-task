package com.mjc.school.repository;


import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static DataSource instance;

    @Getter
    private final List<News> newsList;

    @Getter
    private final List<Author> authorList;



    private DataSource() {

        authorList = new ArrayList<>(10);
        //TODO: remove and initialize authorsList from files
        Author author1 = new Author(1L, "Rosemary Bailey");
        Author author2 = new Author(2L, "Claire Keegan's Foster");
        Author author3 = new Author(3L, "Leon Abbs");
        authorList.add(author1);
        authorList.add(author2);
        authorList.add(author3);

        newsList = new ArrayList<>(20);

        //TODO: remove and initialize newsList from files
        newsList.add(new News(1L, "MAINE CRIMINAL CODE", "Washington High School students afforded discretion.", LocalDateTime.of(2022, Month.JULY, 11, 19, 30, 30), LocalDateTime.now(), author1));
        newsList.add(new News(2L, "DOMESTIC RELATIONS", "A man with an intestinal tumor offering unconditional.", LocalDateTime.of(2021, Month.JULY, 6, 19, 30, 30), LocalDateTime.now(), author2));
        newsList.add(new News(3L, "JUDICIARY", "The white woman standing her ground, defending an elderly black.", LocalDateTime.of(2022, Month.JULY, 29, 19, 30, 30), LocalDateTime.now(), author2));
        newsList.add(new News(4L, "MAINE CRIMINAL CODE", "Unconditional love through Craigslist ads, and sparking.", LocalDateTime.of(2023, Month.JANUARY, 15, 19, 30, 30), LocalDateTime.now(), author3));
        newsList.add(new News(5L, "PROBATE CODE", "San Francisco's mobile shower truck for the homeless.", LocalDateTime.of(2021, Month.JULY, 7, 19, 30, 30), LocalDateTime.now(), author3));

    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
}
