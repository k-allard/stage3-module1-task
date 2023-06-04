package com.mjc.school.repository.impl;

import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.Author;

import java.time.LocalDateTime;
import java.util.List;

public class RepositoryImpl implements Repository<NewsModel> {

    private final DataSource dataSource = new DataSource();

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModelList();
    }

    @Override
    public NewsModel readById(Long id) throws NewsNotFoundException {
        int indexOfNews = checkNewsId(id);
        return dataSource.getNewsModelList().get(indexOfNews);
    }

    @Override
    public NewsModel create(NewsModel newsModel) throws AuthorNotFoundException {
        checkAuthorId(newsModel.getAuthor().getId());
        dataSource.getNewsModelList().add(newsModel);
        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel newsModel) throws NewsNotFoundException, AuthorNotFoundException {
        int indexOfNews = checkNewsId(newsModel.getId());
        NewsModel newsModelFromRepo = dataSource.getNewsModelList().get(indexOfNews);
        newsModelFromRepo.setTitle(newsModel.getTitle());
        newsModelFromRepo.setContent(newsModel.getContent());
        checkAuthorId(newsModel.getAuthor().getId());
        newsModelFromRepo.setAuthor(newsModel.getAuthor());
        newsModelFromRepo.setLastUpdateDate(LocalDateTime.now());
        return newsModelFromRepo;
    }

    @Override
    public boolean deleteById(Long id) throws NewsNotFoundException {
        checkNewsId(id);
        return dataSource.getNewsModelList().remove(new NewsModel(id));
    }

    @Override
    public int getNextId() {
        return dataSource.getNextNewsId();
    }

    private int checkNewsId(Long id) throws NewsNotFoundException {
        List<NewsModel> newsModelModelList = dataSource.getNewsModelList();
        int indexOfNews = newsModelModelList.indexOf(new NewsModel(id));
        if (indexOfNews == -1) {
            throw new NewsNotFoundException("News with id %d does not exist."
                    .formatted(id));
        }
        return indexOfNews;
    }

    private void checkAuthorId(Long id) throws AuthorNotFoundException {
        int indexOfAuthor = dataSource.getAuthorList().indexOf(new Author(id));
        if (indexOfAuthor == -1) {
            throw new AuthorNotFoundException("Author Id does not exist. Author Id is: %d"
                    .formatted(id));
        }
    }
}
