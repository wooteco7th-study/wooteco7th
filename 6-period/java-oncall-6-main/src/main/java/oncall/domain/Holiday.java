package oncall.domain;

import java.util.Arrays;

public enum Holiday {

    HOLIDAY_1(1, 1),
    HOLIDAY_2(3,1),
    HOLIDAY_3(5, 5),
    HOLIDAY_4(6, 6),
    HOLIDAY_5(8, 15),
    HOLIDAY_6(10, 3),
    HOLIDAY_7(10, 9),
    HOLIDAY_8(12, 25);

    private final int month;
    private final int dayOfWeek;

    Holiday(final int month, final int dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public static boolean isHoliday(final int month, final int dayOfWeek) {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> holiday.month == month && holiday.dayOfWeek == dayOfWeek);
    }
}
