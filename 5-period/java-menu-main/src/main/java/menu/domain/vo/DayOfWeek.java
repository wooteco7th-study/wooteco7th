package menu.domain.vo;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("월요일", 0),
    TUESDAY("화요일", 1),
    WEDNESDAY("수요일", 2),
    THURSDAY("목요일", 3),
    FRIDAY("금요일", 4),
    NONE("없음", 5);
    private final String value;
    private final int index;

    DayOfWeek(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static DayOfWeek findByIndex(final int index) {
        return Arrays.stream(values()).filter(dayOfWeek -> dayOfWeek.getIndex() == index)
                .findFirst()
                .orElse(NONE);
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
