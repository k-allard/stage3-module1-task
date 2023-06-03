package com.mjc.school;

import com.mjc.school.exceptions.IdShouldBeNumberException;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsContentInvalidException;
import com.mjc.school.service.exceptions.NewsNotFoundException;
import com.mjc.school.service.exceptions.NewsTitleInvalidException;

public class MainApplication {

    private static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found.";

    public static void main(String[] args) {
        TerminalCommandsReader commandsReader = new TerminalCommandsReader();

        CommandsExecutor commandsExecutor = new CommandsExecutor();

        while (true) {
            commandsReader.getCommand().ifPresentOrElse(cmd ->
                    {
                        try {
                            commandsExecutor.executeCommand(cmd);
                        } catch (NewsNotFoundException |
                                 AuthorNotFoundException |
                                 NewsTitleInvalidException |
                                 NewsContentInvalidException |
                                 IdShouldBeNumberException e) {
                            System.out.println(e.getMessage());
                        }
                    },
                    () -> System.out.println(COMMAND_NOT_FOUND_MESSAGE));
        }

    }
}
