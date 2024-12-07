package menu.domain.vo;

import java.util.Arrays;

public enum Category {
    JP("일식", 1),
    KR("한식", 2),
    CH("중식", 3),
    ASIA("아시안", 4),
    WEST("양식", 5),
    NONE("", 6);
    private final String value;
    private final int index;

    Category(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Category findByIndex(final int index) {
        return Arrays.stream(values()).filter(category -> category.getIndex() == index)
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
