package christmas.Domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Menu {

    public enum MenuDetail {
        BUTTON_MUSHROOM_SOUP("양송이수프", "에피타이저", 6_000),
        TAPAS("타파스", "에피타이저", 5_500),
        CAESAR_SALAD("시저샐러드", "에피타이저", 8_000),
        T_BONE_STEAK("티본스테이크", "메인", 55_000),
        BARBECUE("바베큐립", "메인", 54_000),
        SEAFOOD_PASTA("해산물파스타", "메인", 35_000),
        CHRISTMAS_PASTA("클리스마스파스타", "메인", 25_000),
        CHOCOLATE_CATE("초코케이크", "디저트", 5_000),
        ICE_CREAM("아이스크림", "디저트", 5_000),
        ZERO_COKE("제로콜라", "음료", 3_000),
        RED_WINE("레드와인", "음료", 60_000),
        CHAMPAGNE("샴페인", "음료", 25_000);

        private final String name;
        private final String type;
        private final int price;

        MenuDetail(String name, String type, int price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        public String getName() {
            return name;
        }
    }

    public static int getPrice(String menuName) {
        return Arrays.stream(MenuDetail.values())
                .filter(menu -> menu.name.equals(menuName))
                .toList().get(0).price;
    }

    public static HashMap<String, Integer> getMenuPrice() {
        HashMap<String, Integer> menus = new HashMap<>();
        MenuDetail[] totalMenu = MenuDetail.values();
        Arrays.stream(totalMenu).forEach(
                menu -> { menus.put(menu.name, menu.price); }
        );
        return menus;
    }

    public static HashMap<String, String> getMenuType() {
        HashMap<String, String> menus = new HashMap<>();
        MenuDetail[] totalMenu = MenuDetail.values();
        Arrays.stream(totalMenu).forEach(
                menu -> { menus.put(menu.name, menu.type); }
        );
        return menus;
    }

    public static List<String> getMenuName() {
        return Arrays.stream(MenuDetail.values()).map(MenuDetail::getName).toList();
    }
}
