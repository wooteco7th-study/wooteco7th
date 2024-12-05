package baseball.domain;

import java.util.Arrays;

import static baseball.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum GameCommand {

    RESTART("1"),
    STOP("2"),
    ;

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand from(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
