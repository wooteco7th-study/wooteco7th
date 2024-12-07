package vendingmachine.domain;

import java.util.List;

import static vendingmachine.exception.ExceptionMessage.INVENTORY_DUPLICATED;
import static vendingmachine.exception.ExceptionMessage.PRODUCT_NOT_EXIST;

public class Inventories {

    private List<Inventory> inventories;

    public Inventories(final List<Inventory> inventories) {
        validateDuplicatedInventory(inventories);
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
                .anyMatch(Inventory::hasStock);
    }

    private boolean validateOrderAmount(final int orderAmount) {
        return orderAmount >= findLowestPrice();
    }

    private int findLowestPrice() {
        return inventories.stream()
                .filter(Inventory::hasStock)
                .mapToInt(Inventory::getProductPrice)
                .min()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_EXIST.getMessage()));
    }

    private void validateDuplicatedInventory(final List<Inventory> inventories) {
        if (isDuplicated(inventories)) {
            throw new IllegalArgumentException(INVENTORY_DUPLICATED.getMessage());
        }
    }

    private boolean isDuplicated(final List<Inventory> inventories) {
        return inventories.stream()
                .distinct()
                .count() != inventories.size();
    }
}
