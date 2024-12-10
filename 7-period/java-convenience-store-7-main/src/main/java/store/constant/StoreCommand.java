package store.constant;

import java.util.Arrays;
import java.util.Objects;
import store.error.AppException;
import store.error.ErrorMessage;

public enum StoreCommand {
    YES("Y"),
    NO("N");

    private final String command;

    StoreCommand(final String command) {
        this.command = command;
    }

    public static StoreCommand findByCommand(final String command) {
        return Arrays.stream(StoreCommand.values())
                .filter(storeCommand -> Objects.equals(storeCommand.command, command))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_INPUT));
    }
}
