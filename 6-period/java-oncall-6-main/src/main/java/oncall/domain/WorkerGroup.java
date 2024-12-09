package oncall.domain;

public class WorkerGroup {

    private final WeekdayWorkerGroup weekdayWorkerGroup;
    private final WeekendWorkerGroup weekendWorkerGroup;

    public WorkerGroup(final WeekdayWorkerGroup weekdayWorkerGroup, final WeekendWorkerGroup weekendWorkerGroup) {
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
}
