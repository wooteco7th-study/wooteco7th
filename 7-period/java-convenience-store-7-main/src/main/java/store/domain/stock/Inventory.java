package store.domain.stock;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import store.domain.promotion.Promotion;

public class Inventory {

    private final Map<String, Stocks> inventory;

    public Inventory(final Map<String, Stocks> inventory) {
        this.inventory = new LinkedHashMap<>(inventory);
    }

    // name,price,quantity,promotion
    // 프로모션 존재할 경우
    public void put(final String name, final int price, final int quantity, final Promotion promotion) {
        if (inventory.containsKey(name)) {
            Stocks stocks = inventory.get(name);
            stocks.add(quantity, promotion);
            return;
        }
        inventory.put(name, Stocks.from(price, quantity, promotion));
    }

    public Map<String, Stocks> getInventory() {
        return Collections.unmodifiableMap(inventory);
    }
}
