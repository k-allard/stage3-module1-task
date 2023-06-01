package com.mjc.school;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.service.dto.NewsCreateDTORequest;
import com.mjc.school.service.dto.NewsUpdateDTORequest;

public class CommandsExecutor {

    NewsController newsController = new NewsControllerImpl();

    TerminalCommandsReader commandsReader = new TerminalCommandsReader();

    public void executeCommand(Command command) {
        if (command == Command.EXIT)
            System.exit(0);
        System.out.print("Operation: ");
        System.out.println(command.description);
        switch (command) {
            case GET_ALL: {
                System.out.println(newsController.getAllNews());    //TODO print list with linebreaks
                break;
            }
            case GET_BY_ID: {
                long id = -1L;
                try {
                    id = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                }
                catch (NumberFormatException e) {
                    // TODO throw error if its not a num
                }
                System.out.println(newsController.getNewsById(id));
                break;

            }
            case CREATE: {
                String title = commandsReader.getResponseByPrompt("Enter news title:");
                String content = commandsReader.getResponseByPrompt("Enter news content:");
                // TODO throw error if its not a num
                Long authorId = Long.parseLong(commandsReader.getResponseByPrompt("Enter author id:"));

                System.out.println(newsController.createNews(new NewsCreateDTORequest(title, content, authorId)));
                break;

            }
            case UPDATE: {
                Long newsId = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                String title = commandsReader.getResponseByPrompt("Enter news title:");
                String content = commandsReader.getResponseByPrompt("Enter news content:");
                // TODO throw error if its not a num
                Long authorId = Long.parseLong(commandsReader.getResponseByPrompt("Enter author id:"));
                System.out.println(newsController.updateNews(new NewsUpdateDTORequest(newsId, title, content, authorId)));
                break;

            }
            case REMOVE_BY_ID: {
                Long newsId = Long.parseLong(commandsReader.getResponseByPrompt("Enter news id:"));
                System.out.println(newsController.removeNews(newsId));
                break;

            }
        }
    }
}
