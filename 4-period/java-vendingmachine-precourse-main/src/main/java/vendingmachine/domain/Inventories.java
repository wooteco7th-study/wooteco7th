package vendingmachine.domain;

import java.util.Comparator;
import java.util.List;

import static vendingmachine.exception.ExceptionMessage.PRODUCT_NOT_EXIST;

public class Inventories {

    private List<Inventory> inventories;

    public Inventories(final List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public boolean keepOrder(final int orderAmount) {
        return validateStock() && validateOrderAmount(orderAmount);
    }

    public int update(final int orderAmount, final String orderProductName) {
        Inventory inventory = findInventory(orderProductName);
        inventory.decreaseStock();
        return orderAmount - inventory.getProductPrice();
    }

    private Inventory findInventory(final String orderProductName) {
        return inventories.stream()
                .filter(inventory -> inventory.hasSameName(orderProductName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_EXIST.getMessage()));
    }

    private boolean validateStock() {
        return inventories.stream()
                .allMatch(inventory -> inventory.getStock() > 0);
    }

    private boolean validateOrderAmount(final int orderAmount) {
        return orderAmount >= findLowestPrice();
    }

    private int findLowestPrice() {
        return inventories.stream()
                .min(Comparator.comparingInt(Inventory::getProductPrice))
                .get()
                .getProductPrice();
    }
}
