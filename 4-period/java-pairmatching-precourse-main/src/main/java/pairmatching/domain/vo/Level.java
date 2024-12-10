package pairmatching.domain.vo;

import java.util.Arrays;
import pairmatching.constant.ExceptionMessage;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String value;

    Level(final String value) {
        this.value = value;
    }

    public static Level toLevel(String value) {
        return Arrays.stream(Level.values())
                .filter(option -> option.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage()));
    }

    public String getValue() {
        return value;
    }
}
