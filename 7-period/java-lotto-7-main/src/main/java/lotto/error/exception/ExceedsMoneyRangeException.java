package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class ExceedsMoneyRangeException extends AppException {
    public ExceedsMoneyRangeException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
