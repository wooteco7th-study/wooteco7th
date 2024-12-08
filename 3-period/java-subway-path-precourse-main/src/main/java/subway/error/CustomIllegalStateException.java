package subway.error;

public class CustomIllegalStateException extends IllegalStateException {
    public CustomIllegalStateException(final ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
