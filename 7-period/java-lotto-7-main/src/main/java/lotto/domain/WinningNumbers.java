package lotto.domain;

import static lotto.domain.validator.WinningNumbersValidator.validateWinningNumbers;

import java.util.List;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;

public class WinningNumbers {
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(final Lotto lotto, final BonusNumber bonusNumber) {
        validateWinningNumbers(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }


    public List<Integer> getLottoValue() {
        return lotto.getLottoNumbers();
    }

    public int getBonusNumberValue() {
        return bonusNumber.getValue();
    }

}
