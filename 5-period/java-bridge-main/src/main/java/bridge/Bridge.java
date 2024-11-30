package bridge;

import bridge.exception.ErrorMessage;
import bridge.util.NumberValidator;

public class Bridge {

    private final int size;

    public Bridge(final int size) {
        validate(size);
        this.size = size;
    }

    private void validate(final int size) {
        NumberValidator.validateRange(size, 0, Integer.MAX_VALUE, ErrorMessage.INVALID_BRIDGE_LENGTH);
    }
}
