package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class WrongLottoNumberSizeException extends AppException {
    public WrongLottoNumberSizeException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
