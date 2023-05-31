package com.mjc.school;

public class CommandsExecutor {

    public void executeCommand(Command command) {
        System.out.println("<<<<<<<<commandexecutor recieved " + command + ">>>>>>>>");
        if (command == Command.EXIT)
            System.exit(0);
    }
}
