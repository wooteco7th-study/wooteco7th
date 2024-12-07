package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum PairCommand {

    MATCHING("1"),
    LOOK_UP("2"),
    CLEAR("3"),
    QUIT("Q");

    private final String command;

    PairCommand(final String command) {
        this.command = command;
    }

    public static PairCommand findByCommand(final String command) {
        return Arrays.stream(PairCommand.values())
                .filter(pairCommand -> Objects.equals(pairCommand.command, command))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_COMMAND));
    }
}
