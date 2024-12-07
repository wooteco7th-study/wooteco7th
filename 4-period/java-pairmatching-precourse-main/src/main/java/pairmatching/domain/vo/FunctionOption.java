package pairmatching.domain.vo;

import java.util.Arrays;
import pairmatching.constant.ExceptionMessage;

public enum FunctionOption {
    FAIR_MATCHING("1"),
    FAIR_CHECK("2"),
    FAIR_RESET("3"),
    QUIT("Q");

    private final String value;

    FunctionOption(final String value) {
        this.value = value;
    }

    public static FunctionOption toFunctionOption(String value) {
        return Arrays.stream(FunctionOption.values())
                .filter(option -> option.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage()));
    }

    public String getValue() {
        return value;
    }
}
