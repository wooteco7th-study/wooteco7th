package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum LevelType {
    LEVEL_1("레벨1"),
    LEVEL_2("레벨2"),
    LEVEL_3("레벨3"),
    LEVEL_4("레벨4"),
    LEVEL_5("레벨5");

    private final String name;

    LevelType(final String name) {
        this.name = name;
    }

    public static LevelType findByName(final String name) {
        return Arrays.stream(LevelType.values())
                .filter(levelType -> Objects.equals(levelType.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_LEVEL));
    }

    public static List<LevelType> findAll() {
        return Arrays.stream(LevelType.values())
                .toList();
    }

    public String getName() {
        return name;
    }
}
