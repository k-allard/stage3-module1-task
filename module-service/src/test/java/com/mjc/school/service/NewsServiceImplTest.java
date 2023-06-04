package com.mjc.school.service;

import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.dto.NewsUpdateDTORequest;
import com.mjc.school.repository.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.repository.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;
import com.mjc.school.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewsServiceImplTest {
    private static final long NEWS_INITIAL_NUMBER = 20;
    private static final long VALID_NEWS_ID = 3L;
    private static final long INVALID_NEWS_ID = 99L;
    private static final long VALID_AUTHOR_ID = 6L;
    private static final long INVALID_AUTHOR_ID = 66L;
    private static final String VALID_NEWS_TITLE = "VALID TITLE";
    private static final String INVALID_NEWS_TITLE = "Ohhh";
    private static final String VALID_NEWS_CONTENT = "Valid content.";
    private static final String INVALID_NEWS_CONTENT = "Paradoxically, another property commonly attributed to news " +
            "is sensationalism, the disproportionate focus on, and exaggeration of, emotive stories for public " +
            "consumption. This news is also not unrelated to gossip, the human practice of sharing information.";

    private final NewsService newsService = new NewsServiceImpl();

    @Test
    @DisplayName("getAllNews() returns initial list of news")
    void getAllNews() {
        List<NewsDTO> list = newsService.readAll();
        assertEquals(NEWS_INITIAL_NUMBER, list.size());
    }

    @Test
    @DisplayName("getNewsById() returns correct news")
    void getNewsByValidId() throws NewsNotFoundException {
        NewsDTO news = newsService.readById(VALID_NEWS_ID);
        assertEquals(VALID_NEWS_ID, news.getId());
    }

    @Test
    @DisplayName("getNewsById() with invalid id fails")
    void getNewsByInvalidId() {
        assertThrows(NewsNotFoundException.class, () -> newsService.readById(INVALID_NEWS_ID));
    }


    @Test
    @DisplayName("createNews() returns new news")
    void createValidNewsAndCheckResponse() throws NewsTitleInvalidException,
            NewsContentInvalidException, AuthorNotFoundException {
        NewsDTO response = newsService.create(
                new NewsCreateDTORequest(VALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)
        );
        assertEquals(VALID_NEWS_TITLE, response.getTitle());
        assertEquals(VALID_NEWS_CONTENT, response.getContent());
        assertEquals(VALID_AUTHOR_ID, response.getAuthorId());
    }

    @Test
    @DisplayName("createNews() saves new news")
    void createValidNewsAndCheckThatItWasWrittenToRepo() throws NewsTitleInvalidException,
            NewsContentInvalidException, NewsNotFoundException, AuthorNotFoundException {
        NewsDTO responseOfCreate = newsService.create(
                new NewsCreateDTORequest(VALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)
        );
        NewsDTO responseByGet = newsService.readById(responseOfCreate.getId());
        assertEquals(VALID_NEWS_TITLE, responseByGet.getTitle());
        assertEquals(VALID_NEWS_CONTENT, responseByGet.getContent());
        assertEquals(VALID_AUTHOR_ID, responseOfCreate.getAuthorId());
    }

    @Test
    @DisplayName("createNews() with invalid title fails")
    void createNewsWithInvalidTitle() {
        assertThrows(NewsTitleInvalidException.class, () -> newsService.create(
                new NewsCreateDTORequest(INVALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("createNews() with invalid content fails")
    void createNewsWithInvalidContent() {
        assertThrows(NewsContentInvalidException.class, () -> newsService.create(
                new NewsCreateDTORequest(VALID_NEWS_TITLE, INVALID_NEWS_CONTENT, VALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("createNews() with invalid authorId fails")
    void createNewsWithInvalidAuthorId() {
        assertThrows(AuthorNotFoundException.class, () -> newsService.create(
                new NewsCreateDTORequest(VALID_NEWS_TITLE, VALID_NEWS_CONTENT, INVALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("updateNews() returns updated news")
    void updateValidNewsAndCheckResponse() throws NewsTitleInvalidException,
            NewsContentInvalidException, AuthorNotFoundException, NewsNotFoundException {
        NewsDTO response = newsService.update(
                new NewsUpdateDTORequest(VALID_NEWS_ID, VALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)
        );
        assertEquals(VALID_NEWS_ID, response.getId());
        assertEquals(VALID_NEWS_TITLE, response.getTitle());
        assertEquals(VALID_NEWS_CONTENT, response.getContent());
        assertEquals(VALID_AUTHOR_ID, response.getAuthorId());
    }

    @Test
    @DisplayName("updateNews() saves updates")
    void updateValidNewsAndCheckThatItWasWrittenToRepo() throws NewsTitleInvalidException,
            NewsContentInvalidException, NewsNotFoundException, AuthorNotFoundException {
        NewsDTO responseOfUpdate = newsService.update(
                new NewsUpdateDTORequest(VALID_NEWS_ID, VALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)
        );
        NewsDTO responseByGet = newsService.readById(responseOfUpdate.getId());
        assertEquals(VALID_NEWS_ID, responseByGet.getId());
        assertEquals(VALID_NEWS_TITLE, responseByGet.getTitle());
        assertEquals(VALID_NEWS_CONTENT, responseByGet.getContent());
        assertEquals(VALID_AUTHOR_ID, responseByGet.getAuthorId());
    }

    @Test
    @DisplayName("updateNews() with invalid id fails")
    void updateNewsWithInvalidId() {
        assertThrows(NewsNotFoundException.class, () -> newsService.update(
                new NewsUpdateDTORequest(INVALID_NEWS_ID, VALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("updateNews() with invalid title fails")
    void updateNewsWithInvalidTitle() {
        assertThrows(NewsTitleInvalidException.class, () -> newsService.update(
                new NewsUpdateDTORequest(VALID_NEWS_ID, INVALID_NEWS_TITLE, VALID_NEWS_CONTENT, VALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("updateNews() with invalid content fails")
    void updateNewsWithInvalidContent() {
        assertThrows(NewsContentInvalidException.class, () -> newsService.update(
                new NewsUpdateDTORequest(VALID_NEWS_ID, VALID_NEWS_TITLE, INVALID_NEWS_CONTENT, VALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("updateNews() with invalid authorId fails")
    void updateNewsWithInvalidAuthorId() {
        assertThrows(AuthorNotFoundException.class, () -> newsService.update(
                new NewsUpdateDTORequest(VALID_NEWS_ID, VALID_NEWS_TITLE, VALID_NEWS_CONTENT, INVALID_AUTHOR_ID)));
    }

    @Test
    @DisplayName("removeNews() deletes news")
    void removeNewsWithValidId() throws NewsNotFoundException {
        newsService.readById(VALID_NEWS_ID);
        assertTrue(newsService.delete(VALID_NEWS_ID));
        assertThrows(NewsNotFoundException.class, () -> newsService.readById(VALID_NEWS_ID));
    }

    @Test
    @DisplayName("removeNews() with invalid news id fails")
    void removeNewsWithInvalidId() {
        assertThrows(NewsNotFoundException.class, () -> newsService.delete(INVALID_NEWS_ID));
    }
}
