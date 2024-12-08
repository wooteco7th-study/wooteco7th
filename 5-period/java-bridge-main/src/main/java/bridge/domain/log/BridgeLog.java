package bridge.domain.log;

import bridge.domain.MoveCommand;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class BridgeLog {

    protected final List<BridgeLogType> values;

    protected BridgeLog(final List<BridgeLogType> values) {
        this.values = values;
    }

    public abstract void updateLog(final MoveCommand moveCommand, final boolean isPassed);

    public List<BridgeLogType> getValues() {
        return Collections.unmodifiableList(values);
    }

    public int countPass() {
        return (int) values.stream()
                .filter(BridgeLogType::isPassed)
                .count();
    }

    public boolean hasFail() {
        return values.stream()
                .anyMatch(value -> Objects.equals(value, BridgeLogType.FAIL));
    }

    public void clear() {
        values.clear();
    }
}
