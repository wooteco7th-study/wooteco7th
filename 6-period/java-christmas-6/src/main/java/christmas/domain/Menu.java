package christmas.domain;

import static christmas.domain.MenuType.APPETIZER;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.DRINK;
import static christmas.domain.MenuType.MAIN;

import christmas.error.AppException;
import christmas.error.ErrorMessage;
import java.util.Arrays;
import java.util.Objects;

public enum Menu {

    /**
     * <애피타이저>
     * 양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
     *
     * <메인>
     * 티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
     *
     * <디저트>
     * 초코케이크(15,000), 아이스크림(5,000)
     *
     * <음료>
     * 제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
     */
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COLA(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000);


    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(final MenuType menuType, final String name, final int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static Menu findByName(final String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Objects.equals(menu.name, name))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorMessage.INVALID_WRONG_ORDER_FORMAT));
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
