package subway.domain;

import java.util.Arrays;

import static subway.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum PathCommand {
    최단거리("1"),
    최소시간("2"),
    돌아가기("B");
    private final String command;

    PathCommand(final String command) {
        this.command = command;
    }

    public static PathCommand from(String input) {
        return Arrays.stream(PathCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
