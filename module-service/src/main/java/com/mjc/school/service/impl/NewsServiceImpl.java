package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.impl.RepositoryImpl;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;
import com.mjc.school.service.mapper.NewsModelDTOMapper;
import com.mjc.school.service.validator.NewsRequestDTOValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final NewsRequestDTOValidator newsValidator = new NewsRequestDTOValidator();

    private final Repository<NewsModel> newsRepository = new RepositoryImpl();

    private final NewsModelDTOMapper mapper = new NewsModelDTOMapper();

    @Override
    public List<NewsDTO> readAll() {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (NewsModel newsModel : newsRepository.readAll()) {
            newsDTOList.add(mapper.mapModelToDto(newsModel));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO readById(Long id) throws NewsNotFoundException {
        NewsModel newsModel = newsRepository.readById(id);
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public NewsDTO create(NewsCreateDTORequest news) throws
            NewsTitleInvalidException,
            NewsContentInvalidException, AuthorNotFoundException {

        newsValidator.validateNewsCreateDTORequest(news);

        NewsDTO newNews =
                new NewsDTO(
                        (long) newsRepository.getNextId(),
                        news.getTitle(),
                        news.getContent(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        news.getAuthorId());
        return mapper.mapModelToDto(newsRepository.create(
                mapper.mapDtoToModel(newNews)
        ));
    }

    @Override
    public NewsDTO update(NewsUpdateDTORequest news) throws
            NewsNotFoundException,
            AuthorNotFoundException,
            NewsTitleInvalidException,
            NewsContentInvalidException {

        newsValidator.validateNewsUpdateDTORequest(news);

        return mapper.mapModelToDto(
                newsRepository.update(
                        mapper.mapRequestDtoToModel(news)
                ));
    }

    @Override
    public boolean delete(Long id) throws NewsNotFoundException {
        return newsRepository.deleteById(id);
    }
}
