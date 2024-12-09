package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;
import oncall.error.ErrorMessage;
import oncall.util.ListValidator;
import oncall.util.NumberValidator;

public class WeekendWorkerGroup {

    private static final int NAME_MIN = 0;
    private static final int NAME_MAX = 5;
    private static final int MIN = 5;
    private static final int MAX = 35;

    private final ArrayDeque<String> workers;

    public WeekendWorkerGroup(final List<String> workers) {
        validate(workers);
        this.workers = new ArrayDeque<>(workers);
    }

    public String getNextWorker() {
        final String worker = workers.pollFirst();
        workers.offerLast(worker);
        return worker;
    }

    public String getChangedWorker(final String worker) {
        final String nextWorker = workers.pollFirst();
        workers.offerLast(nextWorker);
        workers.offerFirst(worker);
        return nextWorker;
    }

    private void validate(final List<String> workers) {
        ListValidator.validateDuplicate(workers, ErrorMessage.INVALID_INPUT);
        NumberValidator.validateRange(workers.size(), MIN, MAX, ErrorMessage.INVALID_INPUT);
        validateLength(workers);
    }

    private void validateLength(final List<String> workers) {
        for (String worker : workers) {
            NumberValidator.validateRange(worker.length(), NAME_MIN, NAME_MAX, ErrorMessage.INVALID_INPUT);
        }
    }
}
