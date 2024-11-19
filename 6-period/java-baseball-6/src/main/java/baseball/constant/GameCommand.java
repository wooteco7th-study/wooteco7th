package baseball.constant;

import baseball.error.AppException;
import baseball.error.ErrorMessage;
import java.util.Arrays;
import java.util.Objects;

public enum GameCommand {

    RESTART("1"),
    QUIT("2");

    private final String input;

    GameCommand(final String input) {
        this.input = input;
    }

    public static GameCommand findByInput(final String input) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> Objects.equals(gameCommand.input, input))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WRONG_GAME_COMMAND));
    }
}
