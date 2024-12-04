package subway.domain;

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
        throw new IllegalArgumentException("올바른 기준을 선택하세요.");
    }
}
