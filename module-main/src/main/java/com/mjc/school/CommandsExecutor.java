package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.exceptions.IdShouldBeNumberException;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsNotFoundException;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;

public class CommandsExecutor {

    NewsController newsController = new NewsControllerImpl();

    TerminalCommandsReader commandsReader = new TerminalCommandsReader();

    public void executeCommand(Command command)
            throws IdShouldBeNumberException, NewsNotFoundException,
            AuthorNotFoundException {
        if (command == Command.EXIT)
            System.exit(0);
        System.out.print("Operation: ");
        System.out.println(command.description);
        switch (command) {
            case GET_ALL -> {
                for (NewsDTOResponse news : newsController.getAllNews()) {
                    System.out.println(news);
                }
            }
            case GET_BY_ID -> {
                long id;
                try {
                    id = Long.parseLong(
                            commandsReader.requestResponseByPrompt("Enter news id:"));
                } catch (NumberFormatException e) {
                    throw new IdShouldBeNumberException("News Id should be number", e);
                }
                System.out.println(newsController.getNewsById(id));

            }
            case CREATE -> System.out.println(
                    newsController.createNews(
                            new NewsCreateDTORequest(
                                    requestNewsTitle(),
                                    requestNewsContent(),
                                    requestAuthorId()
                            )));
            case UPDATE -> {
                Long newsId = Long.parseLong(
                        commandsReader.requestResponseByPrompt("Enter news id:"));
                System.out.println(
                        newsController.updateNews(
                                new NewsUpdateDTORequest(
                                        newsId,
                                        requestNewsTitle(),
                                        requestNewsContent(),
                                        requestAuthorId()
                                )));
            }
            case REMOVE_BY_ID -> {
                long newsId;
                try {
                    newsId = Long.parseLong(commandsReader.requestResponseByPrompt("Enter news id:"));
                } catch (NumberFormatException e) {
                    throw new IdShouldBeNumberException("News Id should be number", e);
                }
                System.out.println(newsController.removeNews(newsId));
            }
        }
    }

    private long requestAuthorId() {
        try {
            return Long.parseLong(commandsReader.requestResponseByPrompt("Enter author id:"));
        } catch (NumberFormatException e) {
            throw new IdShouldBeNumberException("Author Id should be number", e);
        }
    }

    private String requestNewsContent() {
        return commandsReader.requestResponseByPrompt("Enter news content:");
    }

    private String requestNewsTitle() {
        return commandsReader.requestResponseByPrompt("Enter news title:");
    }
}
