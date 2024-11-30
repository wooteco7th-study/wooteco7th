package bridge.domain;

import java.util.ArrayList;
import java.util.Objects;

public class UpBridgeLog extends BridgeLog{

    public UpBridgeLog() {
        super(new ArrayList<>());
    }

    @Override
    public void updateLog(final MoveCommand moveCommand, final boolean isPassed) {
        BridgeLogType bridgeLogType = BridgeLogType.NONE;
        if (Objects.equals(moveCommand, MoveCommand.UP)) {
            if (isPassed) {
                bridgeLogType = BridgeLogType.PASS;
            }
            if (!isPassed) {
                bridgeLogType = BridgeLogType.FAIL;
            }
        }
        super.values.add(bridgeLogType);
    }
}
