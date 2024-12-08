package bridge.domain.bridge;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;

public class BridgeSize {

    private static final int LOWER_INCLUSIVE = 3;
    private static final int UPPER_INCLUSIVE = 20;

    private final int size;

    public BridgeSize(final int size) {
        validate(size);
        this.size = size;
    }

    private void validate(final int size) {
        if (size < LOWER_INCLUSIVE || size > UPPER_INCLUSIVE) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_BRIDGE_LENGTH);
        }
    }

    public int getSize() {
        return size;
    }
}
