package oncall.domain;

public class ScheduleGroup {

    private final Schedule weekdaySchedule;
    private final Schedule weekendSchedule;

    public ScheduleGroup(final Schedule weekdaySchedule, final Schedule weekendSchedule) {
        this.weekdaySchedule = weekdaySchedule;
        this.weekendSchedule = weekendSchedule;
    }

    public String getWeekdayNextWorker() {
        return weekdaySchedule.getNextWorker();
    }

    public String getWeekdayChangedNextWorker(final String worker) {
        return weekdaySchedule.getChangedNextWorker(worker);
    }

    public String getWeekendNextWorker() {
        return weekendSchedule.getNextWorker();
    }

    public String getWeekendChangedNextWorker(final String worker) {
        return weekendSchedule.getChangedNextWorker(worker);
    }
}
