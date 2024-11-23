package christmas.domain.vo;

import static christmas.domain.vo.MenuType.APPETIZER;
import static christmas.domain.vo.MenuType.DESSERT;
import static christmas.domain.vo.MenuType.DRINK;
import static christmas.domain.vo.MenuType.MAIN;
import static christmas.rule.ErrorMessage.INVALID_NO_EXIST_MENU;

import java.util.Arrays;


public enum Menu {

    양송이수프(APPETIZER, "양송이수프", 6_000),
    타파스(APPETIZER, "타파스", 5_500),
    시저샐러드(APPETIZER, "시저샐러드", 8_000),


    티본스테이크(MAIN, "티본스테이크", 55_000),
    바비큐립(MAIN, "바비큐립", 54_000),
    해산물파스타(MAIN, "해산물파스타", 35_000),
    크리스마스파스타(MAIN, "크리스마스파스타", 25_000),


    초코케이크(DESSERT, "초코케이크", 15_000),
    아이스크림(DESSERT, "아이스크림", 5_000),


    제로콜라(DRINK, "제로콜라", 3_000),
    레드와인(DRINK, "레드와인", 60_000),
    샴페인(DRINK, "샴페인", 25_000),


    NONE(MenuType.NONE, "없음", 0);


    private final MenuType menuType;
    private final String menuName;
    private final int price;

    Menu(final MenuType menuType, final String menuName, final int price) {
        this.menuType = menuType;
        this.menuName = menuName;
        this.price = price;
    }


    public static Menu of(String menuName) {
        return Arrays.stream(values()).filter(menu -> menu.getMenuName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NO_EXIST_MENU.getMessage()));
    }

    public int totalMoney(int quantity) {
        return this.price * quantity;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }
}
