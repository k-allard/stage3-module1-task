package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

public class NewsRequestDTOValidator {
    public void validateNewsCreateDTORequest(NewsCreateDTORequest dto) throws
            NewsTitleInvalidException, NewsContentInvalidException {
        checkNewsTitle(dto.getTitle());
        checkNewsContent(dto.getContent());

    }

    public void validateNewsUpdateDTORequest(NewsUpdateDTORequest dto) throws
            NewsContentInvalidException, NewsTitleInvalidException {
        checkNewsTitle(dto.getTitle());
        checkNewsContent(dto.getContent());

    }

    private void checkNewsTitle(String title) throws NewsTitleInvalidException {
        if (title.length() < 5 || title.length() > 30) {
            throw new NewsTitleInvalidException("News title can not be less than 5 and more than 30 symbols. " +
                    "News title is " +
                    title);
        }
    }

    private void checkNewsContent(String content) throws NewsContentInvalidException {
        if (content.length() < 5 || content.length() > 255) {
            throw new NewsContentInvalidException("News content can not be less than 5 and more than 255 symbols. " +
                    "News content is " +
                    content);
        }
    }
}
