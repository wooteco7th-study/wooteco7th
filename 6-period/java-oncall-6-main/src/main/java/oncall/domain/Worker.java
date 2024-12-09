package oncall.domain;

public class Worker {

    private final String name;
    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final int dayOfMonth;
    private final boolean isHoliday;

    public Worker(final String name, final Month month, final DayOfWeek dayOfWeek, final int dayOfMonth, final boolean isHoliday) {
        this.name = name;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
        this.isHoliday = isHoliday;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public String getName() {
        return name;
    }
}
