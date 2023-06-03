package com.mjc.school.service.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.NewsService;
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

    private final NewsRepository repository = new NewsRepositoryImpl();

    @Override
    public List<NewsDTOResponse> getAllNews() {
        List<NewsDTOResponse> newsDTOList = new ArrayList<>();
        for (News news : repository.getNewsList()) {
            newsDTOList.add(mapper.mapModelToDto(news));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTOResponse getNewsById(Long id) throws NewsNotFoundException {
        int indexOfNews = checkNewsId(id);
        News newsModel = repository.getNewsList().get(indexOfNews);
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public NewsDTOResponse createNews(NewsCreateDTORequest news) throws
            NewsTitleInvalidException,
            NewsContentInvalidException, AuthorNotFoundException {

        validator.validateNewsCreateDTORequest(news);
        checkAuthorId(news.getAuthorId());

        NewsDTOResponse newNews =
                new NewsDTOResponse(
                        (long) repository.getNextNewsId(),
                        news.getTitle(),
                        news.getContent(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        news.getAuthorId());
        repository.getNewsList().add(
                mapper.mapDtoToModel(newNews)
        );
        return newNews;
    }

    @Override
    public NewsDTOResponse updateNews(NewsUpdateDTORequest news) throws
            NewsNotFoundException,
            AuthorNotFoundException,
            NewsTitleInvalidException,
            NewsContentInvalidException {

        validator.validateNewsUpdateDTORequest(news);

        int indexOfNews = checkNewsId(news.getId());
        News newsModel = repository.getNewsList().get(indexOfNews);
        newsModel.setTitle(news.getTitle());
        newsModel.setContent(news.getContent());
        int indexOfAuthor = checkAuthorId(news.getAuthorId());
        newsModel.setAuthor(repository.getAuthorList().get(indexOfAuthor));
        newsModel.setLastUpdateDate(LocalDateTime.now());

        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public boolean removeNews(Long id) throws NewsNotFoundException {
        List<News> newsModelList = repository.getNewsList();
        checkNewsId(id);
        return newsModelList.remove(new News(id));
    }

    private int checkAuthorId(Long authorId) throws AuthorNotFoundException {
        int indexOfAuthor = repository.getAuthorList().indexOf(new Author(authorId));
        if (indexOfAuthor == -1) {
            throw new AuthorNotFoundException("Author Id does not exist. Author Id is: %d"
                    .formatted(authorId));
        }
        return indexOfAuthor;
    }

    private int checkNewsId(Long newsId) throws NewsNotFoundException {
        List<News> newsModelList = repository.getNewsList();
        int indexOfNews = newsModelList.indexOf(new News(newsId));
        if (indexOfNews == -1) {
            throw new NewsNotFoundException("News with id %d does not exist."
                    .formatted(newsId));
        }
        return indexOfNews;
    }
}
