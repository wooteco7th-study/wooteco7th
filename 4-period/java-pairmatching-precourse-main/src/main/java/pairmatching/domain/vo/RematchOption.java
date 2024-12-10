package pairmatching.domain.vo;

import java.util.Arrays;
import pairmatching.constant.ExceptionMessage;

public enum RematchOption {
    YES("네"),
    NO("아니오");

    private final String value;

    RematchOption(final String value) {
        this.value = value;
    }


    public static RematchOption toRematchOption(String value) {
        return Arrays.stream(RematchOption.values())
                .filter(option -> option.getValue().equals(value.trim()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_INPUT_FORM.getMessage()));
    }

    public String getValue() {
        return value;
    }
}
