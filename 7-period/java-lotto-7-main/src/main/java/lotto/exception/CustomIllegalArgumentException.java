package lotto.exception;

public class CustomIllegalArgumentException extends IllegalArgumentException {

    public CustomIllegalArgumentException(final ErrorMessage errorMessage) {
        super(ErrorPrefix.format(errorMessage.getMessage()));
    }
}
