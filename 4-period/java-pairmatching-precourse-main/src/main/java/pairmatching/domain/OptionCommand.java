package pairmatching.domain;

import java.util.Arrays;

import static pairmatching.exception.ExceptionMessage.COMMAND_NOT_FOUND;

public enum OptionCommand {
    페어매칭("1"),
    페어조회("2"),
    페어초기화("3"),
    종료("Q");

    private final String command;

    OptionCommand(final String command) {
        this.command = command;
    }

    public static OptionCommand from(String input) {
        return Arrays.stream(OptionCommand.values())
                .filter(element -> element.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }
}
