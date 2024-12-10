package store.domain.stock;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import store.domain.promotion.Promotion;
import store.exception.CustomIllegalArgumentException;
import store.exception.ErrorMessage;

public class Inventory {

    private final Map<String, Stocks> inventory;

    public Inventory(final Map<String, Stocks> inventory) {
        this.inventory = new LinkedHashMap<>(inventory);
    }

    public Stocks get(final String name) {
        if (inventory.containsKey(name)) {
            return inventory.get(name);
        }
        throw new CustomIllegalArgumentException(ErrorMessage.INVALID_PRODUCT_NAME);
    }

    public void put(final String name, final int price, final int quantity, final Promotion promotion) {
        if (inventory.containsKey(name)) {
            Stocks stocks = inventory.get(name);
            stocks.add(quantity, promotion);
            return;
        }
        inventory.put(name, Stocks.from(price, quantity, promotion));
    }

    public boolean hasProduct(final String productName) {
        return inventory.containsKey(productName);
    }

    public Map<String, Stocks> getInventory() {
        return Collections.unmodifiableMap(inventory);
    }

    public void validateAvailablePurchase(final String name, final int quantity) {
        Stocks stocks = inventory.get(name);
        if (!stocks.hasTotalEnoughQuantity(quantity)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_QUANTITY_OUT_OF_STOCK);
        }
    }
}
