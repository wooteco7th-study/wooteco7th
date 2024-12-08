package bridge.domain;

import bridge.error.ErrorMessage;
import bridge.util.NumberValidator;

public class BridgeLength {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    private final int value;

    public BridgeLength(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        NumberValidator.validateRange(value, MIN_LENGTH, MAX_LENGTH, ErrorMessage.INVALID_BRIDGE_LENGTH_FORMAT);
    }

    public int getValue() {
        return value;
    }
}
