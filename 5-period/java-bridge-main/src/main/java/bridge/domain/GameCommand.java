package bridge.domain;

import java.util.Arrays;

import static bridge.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum GameCommand {
    UP("U"),
    DOWN("D"),
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

    public static String from(int number) {
        if (number == 1) {
            return UP.command;
        }
        if (number == 0) {
            return DOWN.command;
        }
        throw new IllegalStateException(COMMAND_NOT_FOUND.getMessage());
    }

    public String getCommand() {
        return command;
    }
}
