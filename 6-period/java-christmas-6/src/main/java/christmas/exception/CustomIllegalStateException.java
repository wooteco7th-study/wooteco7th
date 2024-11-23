package christmas.exception;

import static christmas.exception.ErrorMessage.ERROR_PREFIX;

public class CustomIllegalStateException extends IllegalStateException {

    public CustomIllegalStateException(final String message) {
        super(ERROR_PREFIX.getMessage() + message);
    }
}
