package lotto.domain.validator;

import static lotto.constant.ExceptionMessage.OUT_OF_RANGE_MESSAGE;
import static lotto.constant.LottoRule.LOTTO_NUMBER_MAX;
import static lotto.constant.LottoRule.LOTTO_NUMBER_MIN;

import lotto.util.NumberUtil;

public class BonusNumberValidator {
    private BonusNumberValidator() {
    }

    public static void validateBonusNumber(final int value) {
        validateRange(value);
    }

    private static void validateRange(final int value) {
        if (NumberUtil.isNotBetweenInclusive(value, LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException(OUT_OF_RANGE_MESSAGE.getMessage());
        }
    }
}
