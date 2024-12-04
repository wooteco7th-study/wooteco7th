package subway.domain.option;

import static subway.message.ExceptionMessage.INVALID_FORMAT;

public enum PathCriteriaOption {
    SHORTEST_DISTANCE("1"),
    MINIMUM_TIME("2"),
    BACK("B");

    private final String code;

    PathCriteriaOption(String code) {
        this.code = code;
    }

    public static PathCriteriaOption from(String input) {
        for (PathCriteriaOption option : values()) {
            if (option.code.equalsIgnoreCase(input)) {
                return option;
            }
        }
        throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
    }
}
