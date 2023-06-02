package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.exceptions.ShouldBeNumberException;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.dto.NewsUpdateDTORequest;

public class CommandsExecutor {

    NewsController newsController = new NewsControllerImpl();

    TerminalCommandsReader commandsReader = new TerminalCommandsReader();

    public void executeCommand(Command command) throws ShouldBeNumberException {
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
                    id = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                } catch (NumberFormatException e) {
                    throw new ShouldBeNumberException("News Id should be number", e);
                }
                System.out.println(newsController.getNewsById(id));

            }
            case CREATE -> {
                // TODO move duplicate code somewhere
                String title = commandsReader.getResponseByPrompt("Enter news title:");
                String content = commandsReader.getResponseByPrompt("Enter news content:");
                long authorId;
                try {
                    authorId = Long.parseLong(commandsReader.getResponseByPrompt("Enter author id:"));
                } catch (NumberFormatException e) {
                    throw new ShouldBeNumberException("Author Id should be number", e);
                }
                System.out.println(newsController.createNews(new NewsCreateDTORequest(title, content, authorId)));

            }
            case UPDATE -> {
                Long newsId = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                // TODO move duplicate code somewhere
                String title = commandsReader.getResponseByPrompt("Enter news title:");
                String content = commandsReader.getResponseByPrompt("Enter news content:");
                long authorId;
                try {
                    authorId = Long.parseLong(commandsReader.getResponseByPrompt("Enter author id:"));
                } catch (NumberFormatException e) {
                    throw new ShouldBeNumberException("Author Id should be number", e);
                }
                System.out.println(newsController.updateNews(new NewsUpdateDTORequest(newsId, title, content, authorId)));

            }
            case REMOVE_BY_ID -> {
                long newsId;
                try {
                    newsId = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                } catch (NumberFormatException e) {
                    throw new ShouldBeNumberException("News Id should be number", e);
                }
                System.out.println(newsController.removeNews(newsId));

            }
        }
    }
}
