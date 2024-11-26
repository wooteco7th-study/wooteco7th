package vendingmachine.domain;

public class Inventory {

    private final Product product;
    private int stock;

    public Inventory(final Product product, final int stock) {
        this.product = product;
        this.stock = stock;
    }
}
