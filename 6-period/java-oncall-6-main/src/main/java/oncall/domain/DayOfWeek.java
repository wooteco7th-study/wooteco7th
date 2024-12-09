package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum DayOfWeek {
    SUNDAY("일", 0, true),
    MONDAY("월", 1, false),
    TUESDAY("화", 2, false),
    WEDNESDAY("수", 3,false),
    THURSDAY("목", 4, false),
    FRIDAY("금", 5, false),
    SATURDAY("토", 6, true);


    private final String name;
    private final int index;
    private final boolean isWeekend;

    DayOfWeek(final String name, final int index, final boolean isWeekend) {
        this.name = name;
        this.index = index;
        this.isWeekend = isWeekend;
    }

    public static DayOfWeek findByName(final String name) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> Objects.equals(dayOfWeek.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WEEK));
    }

    public static DayOfWeek getNext(final DayOfWeek dayOfWeek) {
        final int length = DayOfWeek.values().length;
        final int nextIndex = (dayOfWeek.index + 1) % length;
        return Arrays.stream(DayOfWeek.values())
                .sorted((w1, w2) -> w1.index - w2.index)
                .filter(sortedDayOfWeek -> sortedDayOfWeek.index == nextIndex)
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WEEK));
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
