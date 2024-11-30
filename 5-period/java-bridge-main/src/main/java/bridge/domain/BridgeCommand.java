package bridge.domain;

import java.util.Arrays;

import static bridge.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum BridgeCommand {
    UP("U", 1),
    DOWN("D", 0);

    private final String command;
    private final int number;

    BridgeCommand(final String command, final int number) {
        this.command = command;
        this.number = number;
    }

    public static BridgeCommand from(String input) {
        return Arrays.stream(BridgeCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }

    public static String getCommand(int number) {
        return Arrays.stream(BridgeCommand.values())
                .filter(element -> element.number == number)
                .map(BridgeCommand::getCommand)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(COMMAND_NOT_FOUND.getMessage()));
    }

    public String getCommand() {
        return command;
    }
}
