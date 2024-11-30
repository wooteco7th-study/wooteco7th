package bridge;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;

public class RestartCommand {

    private final char command;

    public RestartCommand(final char command) {
        validate(command);
        this.command = command;
    }

    private void validate(final char command) {
        if (command == 'R' || command == 'Q') {
            return;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.INVALID_RESTART_COMMAND);
    }

    public boolean doesContinue() {
        return command == 'R';
    }

    public char getCommand() {
        return command;
    }
}
