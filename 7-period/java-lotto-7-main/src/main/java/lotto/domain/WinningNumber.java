package lotto.domain;

import java.util.List;
import lotto.error.ErrorMessage;
import lotto.util.NumberValidator;

public class WinningNumber {

    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumber(final Lotto winningLotto, final BonusNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public int countMatchedNumberCount(final List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningLotto::hasNumber)
                .count();
    }

    public boolean isMatchedBonus(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(bonusNumber::isMatchedNumber);
    }

    private void validate(final Lotto winningLotto, final BonusNumber bonusNumber) {
        final List<Integer> numbers = winningLotto.getNumbers();
        final int value = bonusNumber.getValue();
        NumberValidator.validateDuplicate(value, numbers, ErrorMessage.DUPLICATED_NUMBER);
    }
}
