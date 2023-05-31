package com.mjc.school;

public class ModuleMainApplication {

    private static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found.";

    public static void main(String[] args) {

        TerminalCommandsReader commandsReader = new TerminalCommandsReader();

        CommandsExecutor commandsExecutor = new CommandsExecutor();

        while (true) {
            commandsReader.getCommand().ifPresentOrElse(
                    commandsExecutor::executeCommand,
                    () -> System.out.println(COMMAND_NOT_FOUND_MESSAGE));
        }
    }
}
