package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum Level {

    레벨1, 레벨2, 레벨3, 레벨4, 레벨5;

    public static Level from(String input) {
        return Arrays.stream(Level.values())
                .filter(level -> Objects.equals(level.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_LEVEL));
    }
}
