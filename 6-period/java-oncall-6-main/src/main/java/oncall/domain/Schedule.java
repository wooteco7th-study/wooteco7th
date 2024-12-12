package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;
import oncall.error.ErrorMessage;
import oncall.util.ListValidator;
import oncall.util.NumberValidator;

public class Schedule {

    private static final int MIN = 5;
    private static final int MAX = 35;

    private final ArrayDeque<String> workers;

    public Schedule(final List<String> workers) {
        validate(workers);
        this.workers = new ArrayDeque<>(workers);
    }

    public String getNextWorker() {
        final String worker = workers.pollFirst();
        workers.offerLast(worker);
        return worker;
    }

    public String getChangedNextWorker(final String worker) {
        workers.pollLast();
        final String changedWorker = workers.pollFirst();
        workers.offerFirst(worker);
        workers.offerLast(changedWorker);
        return changedWorker;
    }

    private void validate(final List<String> workers) {
        ListValidator.validateDuplicate(workers, ErrorMessage.INVALID_INPUT);
        NumberValidator.validateRange(workers.size(), MIN, MAX, ErrorMessage.INVALID_INPUT);
    }
}
