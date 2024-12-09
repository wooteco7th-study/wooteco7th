package oncall.domain.date;

import java.util.Arrays;
import java.util.Objects;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public enum MonthType {

    JAN(1, 31), FEB(2, 28), MAR(3, 31), APR(4, 30),
    MAY(5, 31), JUN(6, 30), JULY(7, 31),
    AUG(8, 31), SEP(9, 30), OCT(10, 31),
    NOV(11, 30), DEC(12, 31);

    private final int monthNumber;
    private final int lastDayNumber;

    MonthType(final int monthNumber, final int lastDayNumber) {
        this.monthNumber = monthNumber;
        this.lastDayNumber = lastDayNumber;
    }

    public static MonthType from(int monthInput) {
        return Arrays.stream(MonthType.values())
                .filter(monthType -> Objects.equals(monthType.monthNumber, monthInput))
                .findFirst()
                .orElseThrow(() -> new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT));
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getLastDayNumber() {
        return lastDayNumber;
    }
}
