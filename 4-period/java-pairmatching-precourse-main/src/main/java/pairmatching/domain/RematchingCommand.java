package pairmatching.domain;

import java.util.Arrays;

import static pairmatching.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum RematchingCommand {
    YES("네"),
    NO("아니오");

    private final String command;

    RematchingCommand(final String command) {
        this.command = command;
    }

    public static RematchingCommand from(String input) {
        return Arrays.stream(RematchingCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
