package oncall.domain;

public class WorkDay {

    private final Month month;
    private final DayOfWeek dayOfWeek;
    private int dayOfMonth;

    public WorkDay(final Month month, final DayOfWeek dayOfWeek, final int dayOfMonth) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfMonth() {
        final int currentDayOfMonth = this.dayOfMonth;
        this.dayOfMonth++;
        return currentDayOfMonth;
    }

    public boolean isHoliday() {
        return Holiday.isHoliday(month.getNumber(), dayOfMonth) && !dayOfWeek.isWeekend();
    }

    public boolean isWeekend() {
        return dayOfWeek.isWeekend();
    }
}
