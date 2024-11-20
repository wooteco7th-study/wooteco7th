package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class InvalidNumberRangeException extends AppException {
    public InvalidNumberRangeException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
