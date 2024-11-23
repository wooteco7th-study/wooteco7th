package christmas.domain.vo;

import java.util.Map;

public class OrderMenus {
    private final Map<String,Integer> orderMenus;

    public OrderMenus(final Map<String, Integer> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public Map<String, Integer> getOrderMenus() {
        return orderMenus;
    }
}
