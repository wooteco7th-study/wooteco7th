package vendingmachine.domain.price;

import static vendingmachine.exception.ErrorMessage.INVALID_AMOUNT;

import java.util.Objects;
import vendingmachine.exception.CustomIllegalArgumentException;

public class Price {

    protected int amount;

    public Price(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount < 0) {
            throw new CustomIllegalArgumentException(INVALID_AMOUNT);
        }
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

    public int getAmount() {
        return amount;
    }
}
