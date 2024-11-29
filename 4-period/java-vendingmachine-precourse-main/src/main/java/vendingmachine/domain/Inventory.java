package vendingmachine.domain;

import static vendingmachine.exception.ExceptionMessage.OUT_OF_STOCK;
import static vendingmachine.exception.ExceptionMessage.STOCK_OUT_OF_RANGE;

public class Inventory {

    public static final int MINIMUM_STOCK = 1;

    private final Product product;
    private int stock;

    public Inventory(final Product product, final int stock) {
        this.product = product;
        validateStock(stock);
        this.stock = stock;
    }

    public boolean hasSameName(String productName) {
        return product.getName().equals(productName);
    }

    public void decreaseStock() {
        if (!hasStock()) {
            throw new IllegalArgumentException(OUT_OF_STOCK.getMessage());
        }
        stock -= 1;
    }

    public boolean hasStock() {
        return stock >= MINIMUM_STOCK;
    }

    public int getProductPrice() {
        return product.getPrice();
    }

    private void validateStock(final int stock) {
        if (stock < MINIMUM_STOCK) {
            throw new IllegalArgumentException(STOCK_OUT_OF_RANGE.getMessage());
        }
    }
}
