package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum MonthType {

    JAN(1, 31), FEB(2, 29), MAR(3, 31),
    APR(4, 30), MAY(5, 31), JUN(6, 30),
    JUL(7, 31), AUG(8, 31), SEP(9, 30),
    OCT(10, 31), NOV(11, 30), DEC(12, 31);

    private final int value;
    private final int endDay;

    MonthType(final int value, final int endDay) {
        this.value = value;
        this.endDay = endDay;
    }

    public static MonthType from(int input) {
        return Arrays.stream(MonthType.values())
                .filter(monthType -> Objects.equals(monthType.value, input))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }
}
