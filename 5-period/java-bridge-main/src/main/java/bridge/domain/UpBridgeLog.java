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
        if (Objects.equals(moveCommand, MoveCommand.UP)) {
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

    public List<BridgeLogType> getValues() {
        return Collections.unmodifiableList(values);
    }
}
