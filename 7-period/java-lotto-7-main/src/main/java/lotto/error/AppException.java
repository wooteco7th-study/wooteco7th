package lotto.error;

public abstract class AppException extends IllegalArgumentException {
    protected AppException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
