package bridge.domain;

import java.util.List;
import java.util.Objects;

public class DownBridgeLog {

    private final List<BridgeLogType> values;

    public DownBridgeLog(final List<BridgeLogType> values) {
        this.values = values;
    }

    public void updateLog(final MoveCommand moveCommand, final boolean isPassed) {
        if (Objects.equals(moveCommand, MoveCommand.DOWN)) {
            if (isPassed) {
                values.add(BridgeLogType.PASS);
            }
            values.add(BridgeLogType.FAIL);
            return;
        }
        values.add(BridgeLogType.NONE);
    }

    public int countPass() {
        return (int) values.stream()
                .filter(BridgeLogType::isPassed)
                .count();
    }
}
