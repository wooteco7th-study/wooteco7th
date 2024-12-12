package oncall.domain;

import java.util.Objects;

public class Worker {

    private final String name;
    private final WorkDay workDay;

    public Worker(final String name, final WorkDay workDay) {
        this.name = name;
        this.workDay = workDay;
    }

    public boolean isMatchedName(final String name) {
        return Objects.equals(this.name, name);
    }

    public String getName() {
        return name;
    }

    public int getMonth() {
        return workDay.getMonth().getNumber();
    }

    public String getDayOfWeek() {
        return workDay.getDayOfWeek().getName();
    }

    public int getDayOfMonth() {
        return workDay.getDayOfMonth();
    }

    public boolean isWeekdayAndHoliday() {
        return workDay.isWeekdayAndHoliday();
    }
}
