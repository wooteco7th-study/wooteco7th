package oncall.domain;

import oncall.error.ErrorMessage;
import oncall.util.NumberValidator;

public class WorkerGroup {

    private static final int MIN = 5;
    private static final int MAX = 35;

    private final WeekdayWorkerGroup weekdayWorkerGroup;
    private final WeekendWorkerGroup weekendWorkerGroup;

    public WorkerGroup(final WeekdayWorkerGroup weekdayWorkerGroup, final WeekendWorkerGroup weekendWorkerGroup) {
        validate(weekdayWorkerGroup, weekendWorkerGroup);
        this.weekdayWorkerGroup = weekdayWorkerGroup;
        this.weekendWorkerGroup = weekendWorkerGroup;
    }

    public String getWeekDayNextWorker() {
        return weekdayWorkerGroup.getNextWorker();
    }

    public String getWeekDayChangedWorker(final String worker) {
        return weekdayWorkerGroup.getChangedWorker(worker);
    }

    public String getWeekendNextWorker() {
        return weekendWorkerGroup.getNextWorker();
    }

    public String getWeekendChangedWorker(final String worker) {
        return weekendWorkerGroup.getChangedWorker(worker);
    }

    private void validate(final WeekdayWorkerGroup weekdayWorkerGroup, final WeekendWorkerGroup weekendWorkerGroup) {
        final int totalSize = weekdayWorkerGroup.getSize() + weekendWorkerGroup.getSize();
        NumberValidator.validateRange(totalSize, MIN, MAX, ErrorMessage.INVALID_WORKER);
    }
}
