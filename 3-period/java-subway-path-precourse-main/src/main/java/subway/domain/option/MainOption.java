package subway.domain.option;

import static subway.message.ExceptionMessage.INVALID_FORMAT;

public enum MainOption {
    PATH_QUERY("1"),
    QUIT("Q");

    private final String code;

    MainOption(String code) {
        this.code = code;
    }

    public static MainOption from(String input) {
        for (MainOption option : values()) {
            if (option.code.equalsIgnoreCase(input)) {
                return option;
            }
        }
        throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
    }
}
