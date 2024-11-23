package christmas.exception;

import static christmas.exception.ErrorMessage.ERROR_PREFIX;

public class CustomIllegalArgumentException extends IllegalArgumentException {

    public CustomIllegalArgumentException(final String message) {
        super(ERROR_PREFIX.getMessage() + message);
    }
}
