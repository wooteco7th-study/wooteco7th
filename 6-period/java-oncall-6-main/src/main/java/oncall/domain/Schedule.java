package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;

public class Schedule {

    private final ArrayDeque<String> workers;

    public Schedule(final List<String> workers) {
        this.workers = new ArrayDeque<>(workers);
    }

    private void validate(final List<String> workers) {
    }
}
