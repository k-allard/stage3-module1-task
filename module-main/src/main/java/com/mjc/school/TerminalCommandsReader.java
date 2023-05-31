package com.mjc.school;

import java.util.Optional;
import java.util.Scanner;

public class TerminalCommandsReader {

    private static final String PROMPT_ENTER_NUMBER_OF_OPERATION
            = "Enter the number of operation:";

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
        for (Command cmd : Command.values()) {
            if (cmd.code == commandCode)
                return cmd;
        }
        return null;
    }

    private void printCommandPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append(PROMPT_ENTER_NUMBER_OF_OPERATION).append('\n');
        for (Command cmd : Command.values()) {
            sb
                    .append(cmd.code)
                    .append(" - ")
                    .append(cmd.description)
                    .append('\n');
        }
        System.out.print(sb);
    }
}
