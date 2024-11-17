package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class InvalidNumberFormatException extends AppException {
    public InvalidNumberFormatException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
