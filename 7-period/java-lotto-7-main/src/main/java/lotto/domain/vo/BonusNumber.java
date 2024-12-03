package lotto.domain.vo;

import static lotto.domain.validator.BonusNumberValidator.validateBonusNumber;

public class BonusNumber {
    private final int value;

    public BonusNumber(final int value) {
        validateBonusNumber(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
