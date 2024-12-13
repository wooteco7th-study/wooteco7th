package pairmatching.domain.order;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum Course {

    백엔드, 프론트엔드;

    public static Course from(String input) {
        return Arrays.stream(Course.values())
                .filter(course -> Objects.equals(course.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_COURSE));
    }

    public boolean isBackend() {
        return this == 백엔드;
    }

    public boolean isFrontend() {
        return this == 프론트엔드;
    }
}
