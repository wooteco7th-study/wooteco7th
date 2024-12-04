package subway.domain;

public enum LineType {
    TWO_LINE("2호선"),
    TREE_LINE("3호선"),
    SHINBUNDANG_LINE("신분당선");

    private final String name;

    LineType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
