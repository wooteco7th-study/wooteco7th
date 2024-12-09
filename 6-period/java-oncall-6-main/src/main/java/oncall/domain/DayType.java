package oncall.domain;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum DayType {

    월(DayOfWeek.MONDAY), 화(DayOfWeek.TUESDAY), 수(DayOfWeek.WEDNESDAY), 목(DayOfWeek.THURSDAY), 금(DayOfWeek.FRIDAY),
    토(DayOfWeek.SATURDAY), 일(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;

    DayType(final DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static DayType from(String input) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> Objects.equals(dayType.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }
}
