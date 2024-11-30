package bridge;

import bridge.exception.ErrorMessage;
import bridge.util.NumberValidator;
import java.util.List;

public class Bridge {

    private final int size;
    private final BridgeMaker maker;
    private final List<Direction> bridgeDirection;

    public Bridge(final int size, final BridgeMaker maker) {
        validate(size);
        this.size = size;
        this.maker = maker;
        bridgeDirection = makeBridgeDirection();
    }

    private List<Direction> makeBridgeDirection() {
        return Direction.from(maker.makeBridge(size));
    }

    private void validate(final int size) {
        NumberValidator.validateRange(size, 0, Integer.MAX_VALUE, ErrorMessage.INVALID_BRIDGE_LENGTH);
    }
}
