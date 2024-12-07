package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum RematchCommand {

    YES("네"),
    NO("아니오");

    private final String command;

    RematchCommand(final String command) {
        this.command = command;
    }

    public static RematchCommand findByCommand(final String command) {
        return Arrays.stream(RematchCommand.values())
                .filter(rematchCommand -> Objects.equals(rematchCommand.command, command))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_COMMAND));
    }
}
