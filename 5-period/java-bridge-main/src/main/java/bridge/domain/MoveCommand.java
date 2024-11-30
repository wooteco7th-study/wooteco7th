package bridge.domain;

import bridge.error.CustomIllegalArgumentException;
import bridge.error.ErrorMessage;
import java.util.Arrays;
import java.util.Objects;

public enum MoveCommand {

    UP("U"),
    DOWN("D"),
    ;

    private final String expression;

    MoveCommand(final String expression) {
        this.expression = expression;
    }

    public static MoveCommand findByExpression(final String expression) {
        return Arrays.stream(MoveCommand.values())
                .filter(moveCommand -> Objects.equals(moveCommand.expression, expression))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_MOVE_COMMAND_FORMAT));
    }
}
