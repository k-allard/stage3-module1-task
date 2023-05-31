package com.mjc.school;

public class CommandsExecutor {

    public void executeCommand(Command command) {
        if (command == Command.EXIT)
            System.exit(0);
        System.out.print("Operation: ");
        System.out.println(command.description);

    }
}
