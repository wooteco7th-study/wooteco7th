package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum Week {
    SUNDAY("일", 0),
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6);


    private final String name;
    private final int index;

    Week(final String name, final int index) {
        this.name = name;
        this.index = index;
    }

    public static Week findByName(final String name) {
        return Arrays.stream(Week.values())
                .filter(week -> Objects.equals(week.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WEEK));
    }

    public static Week getNext(final Week week) {
        final int length = Week.values().length;
        final int nextIndex = (week.index + 1) % length;
        return Arrays.stream(Week.values())
                .sorted((w1, w2) -> w1.index - w2.index)
                .filter(sortedWeek -> sortedWeek.index == nextIndex)
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WEEK));
    }

}
