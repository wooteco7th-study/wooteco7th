package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class WrongNumberFormatException extends AppException {
    public WrongNumberFormatException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
