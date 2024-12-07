package bridge.domain;

import java.util.Arrays;

import static bridge.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum RetryCommand {
    RESTART("R"),
    QUIT("Q");

    private final String command;

    RetryCommand(final String command) {
        this.command = command;
    }

    public static RetryCommand from(String input) {
        return Arrays.stream(RetryCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
