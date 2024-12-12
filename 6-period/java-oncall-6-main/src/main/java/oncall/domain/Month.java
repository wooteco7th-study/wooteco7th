package oncall.domain;

import java.util.Arrays;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int number;
    private final int lastDayOfMonth;

    Month(final int number, final int lastDayOfMonth) {
        this.number = number;
        this.lastDayOfMonth = lastDayOfMonth;
    }

    public static Month findByNumber(final int number) {
        return Arrays.stream(Month.values())
                .filter(month -> month.number == number)
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_INPUT));
    }

    public int getNumber() {
        return number;
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth;
    }
}
