package bridge.domain.command;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;
import java.util.Arrays;

public enum RestartCommand {

    RESTART("R"), QUIT("Q");

    private final String command;

    RestartCommand(final String command) {
        this.command = command;
    }

    public static RestartCommand from(String command) {
        return Arrays.stream(RestartCommand.values())
                .filter(restartCommand -> restartCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_RESTART_COMMAND));
    }

    public boolean doesContinue() {
        return this == RESTART;
    }
}
