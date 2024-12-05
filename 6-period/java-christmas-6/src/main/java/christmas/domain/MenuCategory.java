package christmas.domain;

public enum MenuCategory {

    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private String categoryName;

    MenuCategory(final String categoryName) {
        this.categoryName = categoryName;
    }
}
