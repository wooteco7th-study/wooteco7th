package subway.command;

import java.util.Arrays;
import java.util.Objects;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public enum RouteCriteriaCommand {

    최단거리("1"), 최소시간("2"), 돌아가기("B");

    private final String value;

    RouteCriteriaCommand(final String value) {
        this.value = value;
    }

    public static RouteCriteriaCommand from(String value) {
        return Arrays.stream(RouteCriteriaCommand.values())
                .filter(command -> Objects.equals(command.value, value))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COMMAND));
    }

    public boolean isGoBack(){
        return this == 돌아가기;
    }
}
