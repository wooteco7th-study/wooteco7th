package subway.domain.line;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LineType {
    TWO_LINE("2호선"),
    TREE_LINE("3호선"),
    SHINBUNDANG_LINE("신분당선");

    private final String name;

    LineType(final String name) {
        this.name = name;
    }

    public static List<String> findAll() {
        return Arrays.stream(LineType.values())
                .map(lineType -> lineType.name)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }
}
