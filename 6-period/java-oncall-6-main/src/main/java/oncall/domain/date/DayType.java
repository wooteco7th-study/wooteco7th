package oncall.domain.date;

import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum DayType {

    월(0), 화(1), 수(2), 목(3), 금(4), 토(5), 일(6);

    private final int number;

    DayType(final int number) {
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
