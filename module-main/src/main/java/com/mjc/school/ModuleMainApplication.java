package com.mjc.school;

import com.mjc.school.exceptions.ShouldBeNumberException;

public class ModuleMainApplication {

    private static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found.";

    public static void main(String[] args) {
        TerminalCommandsReader commandsReader = new TerminalCommandsReader();

        CommandsExecutor commandsExecutor = new CommandsExecutor();

        while (true) {

            commandsReader.getCommand().ifPresentOrElse(cmd ->
                    {
                        try {
                            commandsExecutor.executeCommand(cmd);
                        } catch (ShouldBeNumberException e) {
                            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: " + e.getMessage());
                        }
                    },
                    () -> System.out.println(COMMAND_NOT_FOUND_MESSAGE));


        }

    }
}
