package bridge.domain.bridge;

import static bridge.exception.ErrorMessage.ILLEGAL_STATE;

import bridge.exception.CustomIllegalArgumentException;
import java.util.Arrays;

public enum RightWrong {

    O(true), X(false);

    private final boolean isRight;

    RightWrong(final boolean isRight) {
        this.isRight = isRight;
    }

    public static RightWrong from(boolean isRight) {
        return Arrays.stream(RightWrong.values())
                .filter(rightWrong -> rightWrong.isRight == isRight)
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ILLEGAL_STATE));
    }

    public boolean isRight() {
        return isRight;
    }
}
