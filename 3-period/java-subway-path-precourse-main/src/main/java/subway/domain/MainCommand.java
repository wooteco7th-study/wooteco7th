package subway.domain;

import java.util.Arrays;
import java.util.Objects;
import subway.error.CustomIllegalArgumentException;
import subway.error.ErrorMessage;

public enum MainCommand {
    LOOK_UP("1"),
    QUIT("Q");

    private final String command;

    MainCommand(final String command) {
        this.command = command;
    }

    public static MainCommand findByCommand(final String command) {
        return Arrays.stream(MainCommand.values())
                .filter(mainCommand -> Objects.equals(mainCommand.command, command))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }
}
