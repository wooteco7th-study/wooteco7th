package bridge;

import bridge.exception.CustomIllegalArgumentException;
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

    public boolean isEnd(final int pos) {
        return pos == size;
    }

    private List<Direction> makeBridgeDirection() {
        return Direction.from(maker.makeBridge(size));
    }

    private void validate(final int size) {
        NumberValidator.validateRange(size, 3, 20, ErrorMessage.INVALID_BRIDGE_LENGTH);
    }

    public Direction getDirection(final int pos) {
        if (pos >= bridgeDirection.size()) {
            throw new CustomIllegalArgumentException("잘못 접근했습니다.");
        }
        return bridgeDirection.get(pos);
    }
}
