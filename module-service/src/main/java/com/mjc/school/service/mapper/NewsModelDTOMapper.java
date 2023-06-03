package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.modelmapper.ModelMapper;

public class NewsModelDTOMapper {
    private final ModelMapper mapper = new ModelMapper();

    public NewsDTOResponse mapModelToDto(News news) {
        return mapper.map(news, NewsDTOResponse.class);
    }

    public News mapDtoToModel(NewsDTOResponse news) {
        return mapper.map(news, News.class);
    }

}
