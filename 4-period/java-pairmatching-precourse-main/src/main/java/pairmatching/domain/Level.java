package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum Level {
    LEVEL_1("레벨1"),
    LEVEL_2("레벨2"),
    LEVEL_3("레벨3"),
    LEVEL_4("레벨4"),
    LEVEL_5("레벨5");

    private final String name;

    Level(final String name) {
        this.name = name;
    }

    public static Level findByName(final String name) {
        return Arrays.stream(Level.values())
                .filter(level -> Objects.equals(level.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_LEVEL));
    }

    public String getName() {
        return name;
    }
}
