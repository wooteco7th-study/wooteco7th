package christmas.domain.vo;

public enum MenuType {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료"),
    NONE("없음");

    private final String MenyTypeName;

    MenuType(final String menyTypeName) {
        MenyTypeName = menyTypeName;
    }

    public String getMenyTypeName() {
        return MenyTypeName;
    }
}
