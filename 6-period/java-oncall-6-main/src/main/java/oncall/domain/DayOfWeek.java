package oncall.domain;

import java.util.Arrays;
import java.util.Objects;
import oncall.error.AppException;
import oncall.error.ErrorMessage;

public enum DayOfWeek {

    SUNDAY(0, "일", true),
    MONDAY(1, "월", false),
    TUESDAY(2, "화", false),
    WEDNESDAY(3, "수", false),
    THURSDAY(4, "목", false),
    FRIDAY(5, "금", false),
    SATURDAY(6, "토", true);

    private final int index;
    private final String name;
    private final boolean isWeekend;

    DayOfWeek(final int index, final String name, final boolean isWeekend) {
        this.index = index;
        this.name = name;
        this.isWeekend = isWeekend;
    }

    public DayOfWeek findByName(final String name) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> Objects.equals(dayOfWeek.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_INPUT));
    }

    public DayOfWeek getNextDatOfWeek() {
        final int nextIndex = (this.index + 1) % DayOfWeek.values().length;
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.index == nextIndex)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 요일을 찾는데 에러가 발생했습니다. 다시 실행 해주세요"));
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public String getName() {
        return name;
    }
}
