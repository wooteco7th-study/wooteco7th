package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class DuplicatedLottoNumberException extends AppException {
    public DuplicatedLottoNumberException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
