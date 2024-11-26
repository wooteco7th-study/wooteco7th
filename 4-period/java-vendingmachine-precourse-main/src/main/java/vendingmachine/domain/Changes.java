package vendingmachine.domain;

import java.util.List;

public class Changes {

    private final List<Change> changes;

    public Changes(List<Change> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Change change : changes) {
            stringBuilder.append(change.toString());
        }
        return stringBuilder.toString();
    }
}
