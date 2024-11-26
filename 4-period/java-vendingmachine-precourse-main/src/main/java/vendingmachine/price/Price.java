package vendingmachine.price;

public abstract class Price {

    protected long amount;

    public Price(final long amount) {
        this.amount = amount;
    }

    public abstract long getAmount();
}
