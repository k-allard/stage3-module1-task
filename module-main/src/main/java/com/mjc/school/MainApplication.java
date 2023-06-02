package com.mjc.school;

import com.mjc.school.exceptions.IdShouldBeNumberException;
import com.mjc.school.service.exceptions.AuthorNotFoundException;
import com.mjc.school.service.exceptions.NewsNotFoundException;

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
                        } catch (IdShouldBeNumberException e) {
                            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: " + e.getMessage());
                        } catch (NewsNotFoundException e) {
                            System.out.println("ERROR_CODE: 000001 ERROR_MESSAGE: " + e.getMessage());
                        } catch (AuthorNotFoundException e) {
                            System.out.println("ERROR_CODE: 000002 ERROR_MESSAGE: " + e.getMessage());
                        }
                    },
                    () -> System.out.println(COMMAND_NOT_FOUND_MESSAGE));


        }

    }
}
