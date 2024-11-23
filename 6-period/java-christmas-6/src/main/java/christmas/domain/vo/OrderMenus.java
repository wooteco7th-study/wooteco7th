package christmas.domain.vo;

import java.util.Map;

public class OrderMenus {
    private final Map<Menu, Integer> orderMenus;

    public OrderMenus(final Map<Menu, Integer> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public Map<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }
}
