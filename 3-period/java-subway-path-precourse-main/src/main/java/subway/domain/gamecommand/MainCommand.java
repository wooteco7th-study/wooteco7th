package subway.domain.gamecommand;

import java.util.Arrays;

import static subway.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum MainCommand {

    경로조회("1"),
    종료("Q");
    private final String command;

    MainCommand(final String command) {
        this.command = command;
    }

    public static MainCommand from(String input) {
        return Arrays.stream(MainCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
