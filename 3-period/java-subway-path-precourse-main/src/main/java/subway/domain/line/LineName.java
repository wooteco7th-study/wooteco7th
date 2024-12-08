package subway.domain.line;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;

public enum LineName {

    LINE_2("2호선"), LINE_3("3호선"), LINE_SHINBUNDANG("신분당선");

    private final String name;

    LineName(final String name) {
        this.name = name;
    }

    public static List<LineName> findAll() {
        return List.of(LineName.values());
    }
}
