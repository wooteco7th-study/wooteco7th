package pairmatching.domain.vo;

import static pairmatching.constant.ExceptionMessage.INVALID_INPUT_FORM;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String value;

    Course(final String value) {
        this.value = value;
    }

    public static Course toCourse(String value) {
        return Arrays.stream(Course.values())
                .filter(option -> option.getValue().equals(value.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_FORM.getMessage()));
    }

    public String getValue() {
        return value;
    }
}
