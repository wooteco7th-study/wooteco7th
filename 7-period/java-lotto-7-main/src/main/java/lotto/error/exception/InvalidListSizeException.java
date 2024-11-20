package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class InvalidListSizeException extends AppException {
    public InvalidListSizeException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
