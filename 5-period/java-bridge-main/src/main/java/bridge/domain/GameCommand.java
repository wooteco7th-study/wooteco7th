package bridge.domain;

import java.util.Arrays;

import static bridge.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum GameCommand {
    U("1"),
    D("0"),
    RESTART("R"),
    QUIT("Q"),
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

    public static GameCommand fromName(String name) {
        return Arrays.stream(GameCommand.values())
                .filter(element -> element.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
