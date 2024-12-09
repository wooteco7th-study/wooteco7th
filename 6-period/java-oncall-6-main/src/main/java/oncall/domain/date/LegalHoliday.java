package oncall.domain.date;

import java.util.Arrays;

public enum LegalHoliday {

    신정(1, 1), 삼일절(3, 1), 어린이날(5, 5),
    현충일(6, 6), 광복절(8, 15), 개천절(10, 3),
    한글날(10, 9), 성탄절(12, 25);

    private final int month;
    private final int day;

    LegalHoliday(final int month, final int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isLegalHoliday(int month, int day) {
        return Arrays.asList(values())
                .stream()
                .anyMatch(holiday -> holiday.month == month && holiday.day == day);
    }
}
