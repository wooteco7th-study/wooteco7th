package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class ExceedsLottoNumberRangeException extends AppException {
    public ExceedsLottoNumberRangeException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
