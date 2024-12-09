package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum DayOfWeek {
    SUNDAY("일", 0),
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6);


    private final String name;
    private final int index;

    DayOfWeek(final String name, final int index) {
        this.name = name;
        this.index = index;
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

}
