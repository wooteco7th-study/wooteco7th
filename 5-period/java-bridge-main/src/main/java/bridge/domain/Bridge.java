package bridge.domain;

import java.util.List;
import java.util.Objects;

public class Bridge {

    private final List<String> values;

    public Bridge(final List<String> values) {
        this.values = values;
    }

    public boolean isMatchedMoveCommand(final int idx, final String moveCommand) {
        return Objects.equals(values.get(idx), moveCommand);
    }

}
