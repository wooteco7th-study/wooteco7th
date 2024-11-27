package christmas.Domain;

import java.util.HashMap;

public class Order {

    private final HashMap<String, Integer> menuWithPrice = Menu.getMenuPrice();
    private final HashMap<String, String> menuWithType = Menu.getMenuType();
    private final String menu;
    private final int count;

    public Order(String menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public int getPrice() {
        return menuWithPrice.get(menu) * count;
    }

    private boolean checkType(String type) {
        return menuWithType.get(menu).equals(type);
    }

    public boolean isDessert() {
        return checkType("디저트");
    }

    public boolean isMain() {
        return checkType("메인");
    }

    public boolean isDrink() {
        return checkType("음료");
    }

    public String getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return menu + " " + count + "개";
    }
}
