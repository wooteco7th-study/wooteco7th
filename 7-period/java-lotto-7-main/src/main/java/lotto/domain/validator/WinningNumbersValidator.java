package lotto.domain.validator;

import static lotto.constant.ExceptionMessage.OUT_OF_UNIT_FORM_MESSAGE;
import static lotto.util.ValidationUtils.validateNull;

import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;

public class WinningNumbersValidator {

    private WinningNumbersValidator() {
    }

    public static void validateWinningNumbers(final Lotto lotto, final BonusNumber bonusNumber) {
        validateNull(lotto);
        validateNull(bonusNumber);
        validateDuplicate(lotto, bonusNumber);
    }

    private static void validateDuplicate(final Lotto lotto, final BonusNumber bonusNumber) {
        if (lotto.getLottoNumbers().contains(bonusNumber.getValue())) {
            throw new IllegalArgumentException(OUT_OF_UNIT_FORM_MESSAGE.getMessage());
        }
    }

}
