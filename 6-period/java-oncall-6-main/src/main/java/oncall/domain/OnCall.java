package oncall.domain;

import java.util.Objects;
import java.util.Stack;

public class OnCall {

    private final WorkDay workDay;
    private final WorkerGroup workerGroup;
    private final Stack<Worker> workers;

    public OnCall(final WorkDay workDay, final WorkerGroup workerGroup, final Stack<Worker> workers) {
        this.workDay = workDay;
        this.workerGroup = workerGroup;
        this.workers = workers;
    }

    public void assign() {
        final Month month = workDay.getMonth();
        int lastDayOfMonth = month.getLastDayOfMonth();
        while (lastDayOfMonth-- > 0) {
            final DayOfWeek dayOfWeek = workDay.getDayOfWeek();
            final int dayOfMonth = workDay.getDayOfMonth();
            final boolean isHoliday = isHoliday(dayOfWeek, month.getNumber(), dayOfMonth);
            final String workerName = getWorkerName(dayOfWeek, month.getNumber(), dayOfMonth);
            if (workers.isEmpty()) {
                workers.push(new Worker(workerName, month, dayOfWeek, dayOfMonth, isHoliday));
                continue;
            }
            workers.push(getWorker(workerName, dayOfWeek, month, dayOfMonth, isHoliday));
        }
    }

    public Stack<Worker> getWorkers() {
        return workers;
    }

    private String getWorkerName(final DayOfWeek dayOfWeek, final int month, int dayOfMonth) {
        if (dayOfWeek.isWeekend() || Holiday.isHoliday(month, dayOfMonth)) {
            return workerGroup.getWeekendNextWorker();
        }
        return workerGroup.getWeekDayNextWorker();
    }


    private Worker getWorker(final String workerName, final DayOfWeek dayOfWeek, final Month month, int dayOfMonth,
                             final boolean isHoliday) {
        final String lastWorkerName = workers.peek().getName();
        if (!Objects.equals(workerName, lastWorkerName)) {
            return new Worker(workerName, month, dayOfWeek, dayOfMonth, isHoliday);
        }
        return getChangedWorker(workerName, dayOfWeek, month, dayOfMonth, isHoliday);

    }

    private Worker getChangedWorker(final String workerName, final DayOfWeek dayOfWeek, final Month month,
                                    final int dayOfMonth, final boolean isHoliday) {
        String changedWorkerName = "";
        if (dayOfWeek.isWeekend() || isHoliday) {
            changedWorkerName = workerGroup.getWeekendChangedWorker(workerName);
        }
        if (!dayOfWeek.isWeekend() && !isHoliday) {
            changedWorkerName = workerGroup.getWeekDayChangedWorker(workerName);
        }
        return new Worker(changedWorkerName, month, dayOfWeek, dayOfMonth, isHoliday);
    }


    private boolean isHoliday(final DayOfWeek dayOfWeek, int month, int dayOfMonth) {
        return !dayOfWeek.isWeekend() && Holiday.isHoliday(month, dayOfMonth);
    }

}
