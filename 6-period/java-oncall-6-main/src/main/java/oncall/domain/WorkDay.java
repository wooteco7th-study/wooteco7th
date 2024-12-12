package oncall.domain;

public class WorkDay {

    private final Month month;
    private final DayOfWeek dayOfWeek;
    private final int dayOfMonth;

    public WorkDay(final Month month, final DayOfWeek dayOfWeek, final int dayOfMonth) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
    }

    public boolean isWeekdayAndHoliday() {
        return Holiday.isHoliday(month.getNumber(), dayOfMonth) && !dayOfWeek.isWeekend();
    }

    public boolean isWeekendOrHoliday() {
        return Holiday.isHoliday(month.getNumber(), dayOfMonth) || dayOfWeek.isWeekend();
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
