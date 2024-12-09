package oncall.domain;

public class WorkDay {

    private final Month month;
    private DayOfWeek dayOfWeek;
    private int dayOfMonth;


    public WorkDay(final Month month, final DayOfWeek dayOfWeek, final int dayOfMonth) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        final DayOfWeek currentDayOfWeek = this.dayOfWeek;
        this.dayOfWeek = DayOfWeek.getNext(currentDayOfWeek);
        return currentDayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth++;
    }

    public Month getMonth() {
        return month;
    }
}
