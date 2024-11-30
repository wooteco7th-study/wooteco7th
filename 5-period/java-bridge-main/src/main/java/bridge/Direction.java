package bridge;

import bridge.exception.CustomIllegalArgumentException;
import bridge.exception.ErrorMessage;
import java.util.List;
import java.util.Objects;

public class Direction {

    private static final Character UP = 'U';
    private static final Character DOWN = 'D';

    private final char direction;

    public Direction(final char direction) {
        validate(direction);
        this.direction = from(direction);
    }

    public char from(final char direction) {
        if (direction == 'U') {
            return UP;
        }
        return DOWN;
    }

    public static List<Direction> from(List<String> inputs) {
        return inputs.stream()
                .map(input -> new Direction(input.charAt(0)))
                .toList();
    }

    private void validate(final char direction) {
        if (direction == 'U' || direction == 'D') {
            return;
        }
        throw new CustomIllegalArgumentException(ErrorMessage.INVALID_DIRECTION);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Direction direction1)) {
            return false;
        }
        return direction == direction1.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }

    public char getDirection() {
        return direction;
    }
}
