package bridge.domain.bridge;

import static bridge.exception.ErrorMessage.ILLEGAL_STATE;

import bridge.exception.CustomIllegalArgumentException;
import java.util.Arrays;
import java.util.List;

public enum LogType {

    PASS("O", true), FAIL("X", false), NONE(" ", false);

    private final String mark;
    private final boolean isRight;

    private static final List<LogType> VALUED_LOG_TYPES = Arrays.stream(LogType.values())
            .filter(logType -> logType != NONE)
            .toList();

    LogType(final String mark, final boolean isRight) {
        this.mark = mark;
        this.isRight = isRight;
    }

    public static LogType from(boolean isRight) {
        return VALUED_LOG_TYPES.stream()
                .filter(logType -> logType.isRight == isRight)
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ILLEGAL_STATE));
    }

    public boolean isRight() {
        return isRight;
    }

    public String getMark() {
        return mark;
    }
}
