package com.mjc.school;

import java.util.Optional;
import java.util.Scanner;

public class TerminalCommandsReader {

    public Optional<Command> getCommand() {
        printCommandPrompt();
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            try {
                int commandCode = Integer.parseInt(sc.nextLine());
                Command command = getCommandByCode(commandCode);
                return Optional.ofNullable(command);
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private Command getCommandByCode(int commandCode) {
        for (Command c : Command.values()) {
            if (c.code == commandCode)
                return c;
        }
        return null;
    }

    private void printCommandPrompt() {
        System.out.print("""
                Enter the number of operation:
                1 - Get all news.
                2 - Get news by id.
                3 - Create news.
                4 - Update news.
                5 - Remove news by id.
                0 - Exit.
                """);
    }
}
