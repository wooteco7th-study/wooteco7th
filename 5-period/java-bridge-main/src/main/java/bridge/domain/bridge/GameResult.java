package bridge.domain.bridge;

import java.util.Arrays;
import java.util.Objects;

public enum GameResult {

    성공(true), 실패(false);

    private final boolean isSuccess;

    GameResult(final boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static GameResult from(boolean isSuccess) {
        return Arrays.stream(GameResult.values())
                .filter(upDown -> Objects.equals(upDown.isSuccess, isSuccess))
                .findFirst()
                .get();
    }
}
