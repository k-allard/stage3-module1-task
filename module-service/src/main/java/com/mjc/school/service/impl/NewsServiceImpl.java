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

    private final NewsModelDTOMapper mapper = new NewsModelDTOMapper();

    private final NewsRequestDTOValidator validator = new NewsRequestDTOValidator();

    private final Repository<NewsModel> repository = new RepositoryImpl();

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        for (NewsModel newsModel : repository.readAll()) {
            newsDTOList.add(mapper.mapModelToDto(newsModel));
        }
        return newsDTOList;
    }

    @Override
    public NewsDTO getNewsById(Long id) throws NewsNotFoundException {
        NewsModel newsModel = repository.readById(id);
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public NewsDTO createNews(NewsCreateDTORequest news) throws
            NewsTitleInvalidException,
            NewsContentInvalidException, AuthorNotFoundException {

        validator.validateNewsCreateDTORequest(news);

        NewsDTO newNews =
                new NewsDTO(
                        (long) repository.getNextId(),
                        news.getTitle(),
                        news.getContent(),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        news.getAuthorId());
        return mapper.mapModelToDto(repository.create(
                mapper.mapDtoToModel(newNews)
        ));
    }

    @Override
    public NewsDTO updateNews(NewsUpdateDTORequest news) throws
            NewsNotFoundException,
            AuthorNotFoundException,
            NewsTitleInvalidException,
            NewsContentInvalidException {

        validator.validateNewsUpdateDTORequest(news);

        return mapper.mapModelToDto(
                repository.update(
                        mapper.mapRequestDtoToModel(news)
                ));
    }

    @Override
    public boolean removeNews(Long id) throws NewsNotFoundException {
        return repository.deleteById(id);
    }
}
