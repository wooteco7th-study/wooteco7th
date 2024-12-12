package oncall.domain;

import java.util.Stack;

public class OnCall {

    private final Stack<Worker> workers;
    private final ScheduleGroup scheduleGroup;
    private WorkDay startWorkDay;

    public OnCall(final Stack<Worker> workers, final ScheduleGroup scheduleGroup, final WorkDay startWorkDay) {
        this.workers = workers;
        this.scheduleGroup = scheduleGroup;
        this.startWorkDay = startWorkDay;
    }

    public void assign() {
        final Month month = startWorkDay.getMonth();
        int lastDayOfMonth = month.getLastDayOfMonth();
        for (int day = 1; day <= lastDayOfMonth; day++) {
            pushWorker(month, day);
        }
    }

    public Stack<Worker> getWorkers() {
        return workers;
    }

    private void pushWorker(final Month month, final int day) {
        final String notChangedWorkerName = getNotChangedWorkerName();
        final WorkDay workDay = new WorkDay(month, startWorkDay.getDayOfWeek(), day);
        if (workers.isEmpty()) {
            workers.push(new Worker(notChangedWorkerName, workDay));
            updateStartWorkDay();
            return;
        }
        workers.push(new Worker(getWorkerName(notChangedWorkerName), workDay));
        updateStartWorkDay();
    }

    private void updateStartWorkDay() {
        final int dayOfMonth = startWorkDay.getDayOfMonth();
        final DayOfWeek dayOfWeek = startWorkDay.getDayOfWeek();
        final Month month = startWorkDay.getMonth();
        this.startWorkDay = new WorkDay(month, dayOfWeek.getNextDatOfWeek(), dayOfMonth + 1);
    }

    private String getWorkerName(final String worker) {
        if (workers.peek().isMatchedName(worker)) {
            return getChangedWorkerName(worker);
        }
        return worker;
    }

    private String getChangedWorkerName(final String worker) {
        if (startWorkDay.isWeekendOrHoliday()) {
            return scheduleGroup.getWeekendChangedNextWorker(worker);
        }
        return scheduleGroup.getWeekdayChangedNextWorker(worker);
    }


    private String getNotChangedWorkerName() {
        if (startWorkDay.isWeekendOrHoliday()) {
            return scheduleGroup.getWeekendNextWorker();
        }
        return scheduleGroup.getWeekdayNextWorker();
    }
}
