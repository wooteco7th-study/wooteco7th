package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum DayType {

    월(false), 화(false), 수(false), 목(false), 금(false),
    토(true), 일(true);

    private final boolean isWeekend;

    DayType(final boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public static DayType from(String input) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> Objects.equals(dayType.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }
}
