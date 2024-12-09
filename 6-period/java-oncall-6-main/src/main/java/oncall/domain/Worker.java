package oncall.domain;

public class Worker {

    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final boolean isHoliday;

    public Worker(final Month month, final DayOfWeek dayOfWeek, final boolean isHoliday) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isHoliday() {
        return isHoliday;
    }
}
