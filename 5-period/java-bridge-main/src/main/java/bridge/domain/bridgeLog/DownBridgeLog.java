package bridge.domain.bridgeLog;

import bridge.domain.MoveCommand;
import java.util.ArrayList;
import java.util.Objects;

public class DownBridgeLog extends BridgeLog {

    public DownBridgeLog() {
        super(new ArrayList<>());
    }

    public void updateLog(final MoveCommand moveCommand, final boolean isPassed) {
        BridgeLogType bridgeLogType = BridgeLogType.NONE;
        if (Objects.equals(moveCommand, MoveCommand.DOWN)) {
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
