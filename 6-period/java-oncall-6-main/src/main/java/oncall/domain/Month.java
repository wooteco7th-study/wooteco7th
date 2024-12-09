package oncall.domain;

import java.util.Arrays;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int number;

    Month(final int number) {
        this.number = number;
    }

    public static Month findByNumber(final int number) {
        return Arrays.stream(Month.values())
                .filter(month -> month.number == number)
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_MONTH));
    }

    public int getNumber() {
        return number;
    }
}
