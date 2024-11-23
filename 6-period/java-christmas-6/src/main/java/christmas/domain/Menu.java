package christmas.domain;

import java.util.Arrays;

import static christmas.domain.MenuCategory.APPETIZER;
import static christmas.domain.MenuCategory.DESSERT;
import static christmas.domain.MenuCategory.DRINK;
import static christmas.domain.MenuCategory.MAIN;
import static christmas.exception.ExceptionMessage.INVALID_ORDER;

public enum Menu {

    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_LIP(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),

    CHOCO_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COKE(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000);

    private MenuCategory category;
    private String name;
    private int price;

    Menu(final MenuCategory category, final String name, final int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu from(String input) {
        return Arrays.stream(Menu.values())
                .filter(element -> element.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    public MenuCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
