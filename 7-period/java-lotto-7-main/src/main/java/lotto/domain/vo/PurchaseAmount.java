package lotto.domain.vo;

import static lotto.domain.validator.PurchaseAmountValidator.validatePurchaseAmount;

public class PurchaseAmount {
    private final int value;

    public PurchaseAmount(final int value) {
        validatePurchaseAmount(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
