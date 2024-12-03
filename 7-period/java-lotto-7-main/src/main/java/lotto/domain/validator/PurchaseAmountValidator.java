package lotto.domain.validator;

import static lotto.constant.ExceptionMessage.OUT_OF_POSITIVE_MESSAGE;
import static lotto.constant.ExceptionMessage.OUT_OF_UNIT_FORM_MESSAGE;
import static lotto.constant.LottoRule.PURCHASE_AMOUNT_UNIT;
import static lotto.util.NumberUtil.isNotUnitOf;
import static lotto.util.NumberUtil.isPositive;

public class PurchaseAmountValidator {
    private PurchaseAmountValidator() {
    }

    public static void validatePurchaseAmount(final int value) {
        if (!isPositive(value)) {
            throw new IllegalArgumentException(OUT_OF_POSITIVE_MESSAGE.getMessage());

        }
        if (isNotUnitOf(value, PURCHASE_AMOUNT_UNIT)) {
            throw new IllegalArgumentException(OUT_OF_UNIT_FORM_MESSAGE.getMessage());
        }
    }
}
