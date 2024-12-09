package pairmatching.domain.command;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum RetryCommand {

    네, 아니오;

    public static RetryCommand from(String input) {
        return Arrays.stream(RetryCommand.values())
                .filter(command -> Objects.equals(command.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }

    public boolean isYes() {
        return this == 네;
    }
}
