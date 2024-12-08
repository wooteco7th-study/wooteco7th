package subway.domain;

import java.util.Arrays;
import java.util.Objects;
import subway.error.CustomIllegalArgumentException;
import subway.error.ErrorMessage;

public enum PathCommand {

    SHORTEST_DISTANCE("1"),
    SHORTEST_TIME("2"),
    BACK("B");

    private final String command;

    PathCommand(final String command) {
        this.command = command;
    }

    public static PathCommand findByCommand(final String command) {
        return Arrays.stream(PathCommand.values())
                .filter(pathCommand -> Objects.equals(pathCommand.command, command))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }
}
