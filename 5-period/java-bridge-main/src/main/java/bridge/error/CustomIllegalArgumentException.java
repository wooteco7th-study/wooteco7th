package bridge.error;

public class CustomIllegalArgumentException extends IllegalArgumentException {
    public CustomIllegalArgumentException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
