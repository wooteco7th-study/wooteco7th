package oncall.domain;

import java.util.ArrayDeque;
import java.util.List;
import oncall.error.ErrorMessage;
import oncall.util.ListValidator;

public class WeekendWorkerGroup {

    private final ArrayDeque<String> workers;

    public WeekendWorkerGroup(final List<String> workers) {
        validate(workers);
        this.workers = new ArrayDeque<>(workers);
    }

    private void validate(final List<String> workers) {
        ListValidator.validateDuplicate(workers, ErrorMessage.INVALID_WORKER);
    }

    public int getSize() {
        return workers.size();
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
}
