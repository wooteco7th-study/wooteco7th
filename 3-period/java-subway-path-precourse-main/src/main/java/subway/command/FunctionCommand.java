package subway.command;

import java.util.Arrays;
import java.util.Objects;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public enum FunctionCommand {

    PROCESS("1"), QUIT("Q");

    private final String value;

    FunctionCommand(final String value) {
        this.value = value;
    }

    public static FunctionCommand from(String value) {
        return Arrays.stream(FunctionCommand.values())
                .filter(command -> Objects.equals(command.value, value))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }

    public boolean isQuit() {
        return this == QUIT;
    }
}
