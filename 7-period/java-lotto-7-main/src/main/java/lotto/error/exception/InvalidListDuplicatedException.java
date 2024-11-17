package lotto.error.exception;

import lotto.error.AppException;
import lotto.error.ErrorMessage;

public class InvalidListDuplicatedException extends AppException {
    public InvalidListDuplicatedException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
