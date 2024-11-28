package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_AMOUNT;
import static vendingmachine.exception.ErrorMessage.INVALID_PRICE_SUBTRACT;

import java.util.Objects;
import vendingmachine.exception.CustomIllegalArgumentException;

public class Price {

    protected long amount;

    public Price(final long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final long amount) {
        if (amount < 0) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_AMOUNT);
        }
    }

    public boolean isMoreThanEqual(final Price price) {
        return this.amount >= price.amount;
    }

    public boolean isSmallerThan(final Price price) {
        return this.amount < price.amount;
    }

    public Price subtract(final Price price) {
        if (this.isSmallerThan(price)) {
            throw new CustomIllegalArgumentException(INVALID_PRICE_SUBTRACT);
        }
        return new Price(this.amount - price.amount);
    }

    public Price subtractByCount(Price coinPrice, long count) {
        return subtract(new Price(coinPrice.amount * count));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return amount == price.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public long getAmount() {
        return amount;
    }

    public long divide(final Price price) {
        return this.amount / price.amount;
    }
}
