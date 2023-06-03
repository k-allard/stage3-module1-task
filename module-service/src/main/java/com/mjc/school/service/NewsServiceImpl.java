package com.mjc.school.service;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;
import com.mjc.school.service.mapper.NewsModelDTOMapper;
import com.mjc.school.service.validator.NewsRequestDTOValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsModelDTOMapper mapper = new NewsModelDTOMapper();

    private final NewsRequestDTOValidator validator = new NewsRequestDTOValidator();

    private Long idSequence = 20L;  //TODO maybe do it dynamically calculated ?

    @Override
    public List<NewsDTOResponse> getAllNews() {
        List<NewsDTOResponse> newsDTOList = new ArrayList<>();
        for (News news : DataSource.getInstance().getNewsList()) {
            newsDTOList.add(mapper.mapModelToDto(news));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTOResponse getNewsById(Long id) throws NewsNotFoundException {
        List<News> newsModelList = DataSource.getInstance().getNewsList();
        int indexOfNews = newsModelList.indexOf(new News(id));
        if (indexOfNews == -1) {
            throw new NewsNotFoundException("News with id %d does not exist."
                    .formatted(id));
        }
        News newsModel = newsModelList.get(indexOfNews);
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public NewsDTOResponse createNews(NewsCreateDTORequest news) throws
            NewsTitleInvalidException,
            NewsContentInvalidException {
        validator.validateNewsCreateDTORequest(news);

        NewsDTOResponse newNews =
                new NewsDTOResponse(
                        generateId(),
                        news.getTitle(),
                        news.getContent(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        news.getAuthorId());
        DataSource.getInstance().getNewsList().add(
                mapper.mapDtoToModel(newNews)
        );
        return newNews;
    }

    private Long generateId() {
        idSequence++;
        return idSequence;
    }

    @Override
    public NewsDTOResponse updateNews(NewsUpdateDTORequest news) throws
            NewsNotFoundException,
            AuthorNotFoundException,
            NewsTitleInvalidException,
            NewsContentInvalidException {
        validator.validateNewsUpdateDTORequest(news);

        List<News> newsModelList = DataSource.getInstance().getNewsList();
        int indexOfNews = newsModelList.indexOf(new News(news.getId()));
        if (indexOfNews == -1) {
            throw new NewsNotFoundException("News with id %d does not exist."
                    .formatted(news.getId()));
        }
        News newsModel = newsModelList.get(indexOfNews);
        newsModel.setTitle(news.getTitle());
        newsModel.setContent(news.getContent());
        List<Author> authorList = DataSource.getInstance().getAuthorList();
        int indexOfAuthor = authorList.indexOf(new Author(news.getAuthorId()));
        if (indexOfAuthor == -1) {
            throw new AuthorNotFoundException("Author Id does not exist. Author Id is: %d"
                    .formatted(news.getAuthorId()));
        }
        newsModel.setAuthor(authorList.get(indexOfAuthor));
        newsModel.setLastUpdateDate(LocalDateTime.now());
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public boolean removeNews(Long id) throws NewsNotFoundException {
        List<News> newsModelList = DataSource.getInstance().getNewsList();
        int indexOfNews = newsModelList.indexOf(new News(id));
        if (indexOfNews == -1) {
            throw new NewsNotFoundException("News with id %d does not exist."
                    .formatted(id));
        }
        return newsModelList.remove(new News(id));
    }
}
