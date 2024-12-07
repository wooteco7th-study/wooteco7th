package menu.domain.vo;

public enum Category {
    JP("일식"),
    KR("한식"),
    CH("중식"),
    ASIA("아시안"),
    WEST("양식");
    private final String value;

    Category(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
