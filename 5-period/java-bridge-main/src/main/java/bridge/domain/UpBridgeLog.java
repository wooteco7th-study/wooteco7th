package bridge.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UpBridgeLog {

    private final List<BridgeLogType> values;

    public UpBridgeLog() {
        this.values = new ArrayList<>();
    }

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
        values.add(bridgeLogType);
    }

    public int countPass() {
        return (int) values.stream()
                .filter(BridgeLogType::isPassed)
                .count();
    }

    public List<BridgeLogType> getValues() {
        return Collections.unmodifiableList(values);
    }

    public void clear() {
        values.clear();
    }
}
