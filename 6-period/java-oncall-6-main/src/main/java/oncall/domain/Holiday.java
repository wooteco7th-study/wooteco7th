package oncall.domain;

import java.util.Arrays;
import java.util.Objects;

public enum Holiday {
    DAY_1(1,1),
    DAY_2(3,1),
    DAY_3(5,5),
    DAY_4(6,6),
    DAY_5(8,15),
    DAY_6(10,3),
    DAY_7(10,9),
    DAY_8(12,25);

    private final int month;
    private final int day;

    Holiday(final int month, final int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(final int month, final int day) {
        return Arrays.stream(Holiday.values())
                .anyMatch(holiday -> Objects.equals(holiday.month, month) && Objects.equals(holiday.day, day));
    }


}
