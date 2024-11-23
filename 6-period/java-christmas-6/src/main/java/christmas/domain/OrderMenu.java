package christmas.domain;

public class OrderMenu {

    private final Menu menu;
    private final Quantity quantity;

    public OrderMenu(final Menu menu, final Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity.getValue();
    }
}
