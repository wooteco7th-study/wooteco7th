package bridge.domain;

import java.util.List;
import java.util.Objects;

public class Bridge {

    private final List<MoveCommand> values;

    public Bridge(final List<MoveCommand> values) {
        this.values = values;
    }

    public boolean isMatchedMoveCommand(final int idx, final MoveCommand moveCommand) {
        return Objects.equals(values.get(idx), moveCommand);
    }

    public int getSize() {
        return values.size();
    }

}
