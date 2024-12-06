package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.exception.CustomIllegalArgumentException;
import pairmatching.exception.ErrorMessage;

public enum Course {

    백엔드, 프론트엔드;

    public static Course from(String course) {
        return Arrays.stream(Course.values())
                .filter(upDown -> Objects.equals(upDown.name(), course))
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
