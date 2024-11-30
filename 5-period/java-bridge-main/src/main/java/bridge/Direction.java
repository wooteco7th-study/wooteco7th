package bridge;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;

public class Direction {

    private final char direction;

    public Direction(final char direction) {
        validate(direction);
        this.direction = direction;
    }

    private void validate(final char direction) {
        if (direction == 'U' || direction == 'D') {
            return;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DIRECTION);
    }
}
