package pairmatching.domain;

import java.util.Arrays;

import static pairmatching.exception.ExceptionMessage.INFO_NOT_FOUND;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String input) {
        return Arrays.stream(Course.values())
                .filter(element -> element.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INFO_NOT_FOUND.getMessage()));
    }
}
