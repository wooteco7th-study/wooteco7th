package vendingmachine.domain;

import java.util.List;

import static vendingmachine.exception.ExceptionMessage.PRODUCT_NOT_EXIST;

public class Inventories {

    private List<Inventory> inventories;

    public Inventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public void update(final int orderAmount, final String orderProductName) {

    }

    private void validateProductName(final String orderProductName) {
        inventories.stream()
                .filter(inventory -> inventory.hasSameName(orderProductName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_EXIST.getMessage()));
    }
}
