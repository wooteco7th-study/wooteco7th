package christmas.domain;

public class DiscountResult {
    private final Discount discount;
    private final int amount;

    public DiscountResult(final Discount discount, final int amount) {
        this.discount = discount;
        this.amount = amount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getAmount() {
        return amount;
    }
}
