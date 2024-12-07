package pairmatching.domain;

import java.util.Arrays;
import java.util.Objects;
import pairmatching.error.AppException;
import pairmatching.error.ErrorMessage;

public enum CourseType {

    BACK_END("백엔드"),
    FRONT_END("프론트엔드");


    private final String name;

    CourseType(final String name) {
        this.name = name;
    }

    public static CourseType findByName(final String name) {
        return Arrays.stream(CourseType.values())
                .filter(course -> Objects.equals(course.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_COURSE));
    }

    public String getName() {
        return name;
    }
}
