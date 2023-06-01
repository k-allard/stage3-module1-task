package com.mjc.school.service;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.mapper.NewsModelDTOMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO: service must also validate all incoming fields
public class NewsServiceImpl implements NewsService {

    private final NewsModelDTOMapper mapper = new NewsModelDTOMapper();

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
    public NewsDTOResponse getNewsById(Long id) {
        List<News> newsModelList = DataSource.getInstance().getNewsList();
        News newsModel = newsModelList.get(newsModelList.indexOf(new News(id))); //TODO check id for existence
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public NewsDTOResponse createNews(NewsCreateDTORequest news) {
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
    public NewsDTOResponse updateNews(NewsUpdateDTORequest news) {
        List<News> newsModelList = DataSource.getInstance().getNewsList();
        News newsModel = newsModelList.get(newsModelList.indexOf(new News(news.getId())));
        newsModel.setTitle(news.getTitle());
        newsModel.setContent(news.getContent());
        List<Author> authorList = DataSource.getInstance().getAuthorList();
        int idxOfRequiredAuthorInRepo = authorList.indexOf(new Author(news.getAuthorId()));
        if (idxOfRequiredAuthorInRepo == -1) {
            //TODO throw ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is:
        }
        newsModel.setAuthor(authorList.get(idxOfRequiredAuthorInRepo));
        newsModel.setLastUpdateDate(LocalDateTime.now());
        return mapper.mapModelToDto(newsModel);
    }

    @Override
    public boolean removeNews(Long id) {
        return DataSource.getInstance().getNewsList().remove(new News(id));
    }
}
