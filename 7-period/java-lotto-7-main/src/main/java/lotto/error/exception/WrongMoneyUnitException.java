package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class WrongMoneyUnitException extends AppException {
    public WrongMoneyUnitException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
