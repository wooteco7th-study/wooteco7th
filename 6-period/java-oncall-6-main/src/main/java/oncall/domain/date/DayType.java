package oncall.domain.date;

import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum DayType {

    월(false, 0), 화(false, 1), 수(false, 2), 목(false, 3),
    금(false, 4), 토(true, 5), 일(true, 6);

    private static final int START_DAY_NUMBER = 1;

    private final boolean isWeekend;
    private final int value;

    DayType(final boolean isWeekend, final int value) {
        this.isWeekend = isWeekend;
        this.value = value;
    }

    public static DayType from(String input) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> Objects.equals(dayType.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }

    public static DayType from(int input) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> Objects.equals(dayType.value, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }

    public boolean isWeekend(final int day) {
        DayType dayType = calculateDayType(day);
        return dayType.isWeekend;
    }

    public DayType calculateDayType(final int day) {
        int dayTypeNumber = (day - START_DAY_NUMBER + this.value) % values().length;
        return DayType.from(dayTypeNumber);
    }
}
