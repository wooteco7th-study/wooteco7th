package pairmatching.domain;

import java.util.Arrays;

import static pairmatching.exception.ExceptionMessage.INFO_NOT_FOUND;

public enum Level {
    LEVEL_NONE("없음"),
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String level;

    Level(String level) {
        this.level = level;
    }

    public static Level from(String input) {
        return Arrays.stream(Level.values())
                .filter(element -> element.level.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INFO_NOT_FOUND.getMessage()));
    }

    public boolean isLevel3OrLevel5() {
        return this == LEVEL3 || this == LEVEL5;
    }
}
