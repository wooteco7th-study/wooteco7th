package bridge.domain;

import bridge.error.CustomIllegalArgumentException;
import bridge.error.ErrorMessage;
import java.util.Arrays;
import java.util.Objects;

public enum GameCommand {

    RETRY("R"),
    QUIT("Q");

    private final String expression;

    GameCommand(final String expression) {
        this.expression = expression;
    }

    public static GameCommand findByExpression(final String expression) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> Objects.equals(gameCommand.expression, expression))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_GAME_COMMAND_FORMAT));
    }
}
