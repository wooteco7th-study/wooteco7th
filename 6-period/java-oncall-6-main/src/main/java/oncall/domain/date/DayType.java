package oncall.domain.date;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum DayType {

    월(DayOfWeek.MONDAY, 0), 화(DayOfWeek.TUESDAY, 1), 수(DayOfWeek.WEDNESDAY, 2), 목(DayOfWeek.THURSDAY, 3), 금(
            DayOfWeek.FRIDAY, 4), 토(DayOfWeek.SATURDAY, 5), 일(DayOfWeek.SUNDAY, 6);

    private final DayOfWeek dayOfWeek;
    private final int number;

    DayType(final DayOfWeek dayOfWeek, final int number) {
        this.dayOfWeek = dayOfWeek;
        this.number = number;
    }

    public static DayType from(String input) {
        return Arrays.stream(DayType.values())
                .filter(dayType -> Objects.equals(dayType.name(), input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }

    public DayType passFrom(DayType from, int pass) {
        int passDay = (from.number + pass) % values().length;
        return Arrays.stream(values())
                .filter(dayType -> dayType.number == passDay)
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.CANNOT_CALCULATE_DAYTYPE));
    }

    public boolean isSaturdayOrSunday() {
        return this == 토 || this == 일;
    }
}
