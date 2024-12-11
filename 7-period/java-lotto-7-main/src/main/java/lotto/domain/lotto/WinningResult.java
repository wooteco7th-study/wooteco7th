package lotto.domain.lotto;

import lotto.exception.CustomIllegalArgumentException;
import lotto.exception.ErrorMessage;

public class WinningResult {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningResult(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_BONUS_NUMBER_DUPLICATED);
        }
    }


}
