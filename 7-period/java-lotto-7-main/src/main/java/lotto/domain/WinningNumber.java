package lotto.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public LottoRank calculateLottoRank(final Lotto lotto) {
        final int matchNumberCount = calculateMatchNumberCount(lotto);
        final boolean matchedBonusNumber = isMatchedBonusNumber(lotto);
        return LottoRank.findByMatchNumberCountAndIsMatchedBonus(matchNumberCount, matchedBonusNumber);
    }

    private boolean isMatchedBonusNumber(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        return numbers.stream()
                .anyMatch(bonusNumber::isMatchedNumber);
    }

    private int calculateMatchNumberCount(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        return (int) numbers.stream()
                .filter(winningLotto::hasNumber)
                .count();

    }

    private void validate(final Lotto winningLotto, final BonusNumber bonusNumber) {
        final List<Integer> numbers = winningLotto.getNumbers();
        final int value = bonusNumber.getValue();
        NumberValidator.validateDuplicate(value, numbers, ErrorMessage.INVALID_DUPLICATED_WINNING_AND_BONUS);
    }
}
