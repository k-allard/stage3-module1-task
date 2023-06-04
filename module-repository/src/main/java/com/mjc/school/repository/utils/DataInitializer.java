package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.NewsModel;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataInitializer {
    private static final String FILENAME_AUTHORS = "/author.txt";

    private static final String FILENAME_NEWS_TITLES = "/news.txt";

    private static final String FILENAME_NEWS_CONTENT = "/content.txt";

    private final AtomicInteger idSequence;

    public DataInitializer(AtomicInteger idSequence) {
        this.idSequence = idSequence;
    }

    @SneakyThrows
    public List<NewsModel> initializeNewsList(List<Author> authorList) {
        List<NewsModel> newsModelList = new ArrayList<>(20);

        List<String> titlesLines = readLinesFromFile(FILENAME_NEWS_TITLES);
        List<String> contentLines = readLinesFromFile(FILENAME_NEWS_CONTENT);

        for (String titleLine : titlesLines) {
            int id = idSequence.incrementAndGet();
            String title = titleLine.substring(0, titleLine.indexOf(','));
            String content = contentLines.get(id - 1);
            int authorId = Integer.parseInt(titleLine.substring(titleLine.indexOf(',') + 1).trim());
            newsModelList.add(
                    new NewsModel(
                            (long) id,
                            title,
                            content,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            authorList.get(authorId - 1)
                    ));
        }
        return newsModelList;
    }

    @SneakyThrows
    public List<Author> initializeAuthorList() {
        List<Author> authorList = new ArrayList<>();
        long authorIdSequence = 0;
        List<String> lines = readLinesFromFile(FILENAME_AUTHORS);

        for (String line : lines) {
            authorList.add(new Author(++authorIdSequence, line));
        }

        return authorList;
    }

    private List<String> readLinesFromFile(String filename)
            throws IOException {
        InputStream inputStream = DataInitializer.class.getResourceAsStream(filename);
        if (inputStream == null) {
            throw new RuntimeException(
                    "File %s not found in resources".formatted(filename));
        }
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }
}
