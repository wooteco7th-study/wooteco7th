package vendingmachine.domain;

import java.util.List;

import static vendingmachine.exception.ExceptionMessage.OUT_OF_STOCK;
import static vendingmachine.exception.ExceptionMessage.PRODUCT_NOT_EXIST;

public class Inventories {

    private List<Inventory> inventories;

    public Inventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    //orderAmount = 투입 금액
    public void update(final int orderAmount, final String orderProductName) {
        Inventory inventory = findInventory(orderProductName);
        validateStock(inventory);
    }

    private void validateStock(final Inventory inventory) {
        if (inventory.getStock() < 0) {
            throw new IllegalArgumentException(OUT_OF_STOCK.getMessage());
        }
    }

    private Inventory findInventory(final String orderProductName) {
        return inventories.stream()
                .filter(inventory -> inventory.hasSameName(orderProductName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_EXIST.getMessage()));
    }
}
