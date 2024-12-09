package oncall.domain;

public class WorkDay {

    private final Month month;
    private DayOfWeek dayOfWeek;


    public WorkDay(final Month month, final DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public void updateDayOfWeek(final DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        final DayOfWeek currentDayOfWeek = this.dayOfWeek;
        this.dayOfWeek = DayOfWeek.getNext(currentDayOfWeek);
        return currentDayOfWeek;
    }

    public Month getMonth() {
        return month;
    }
}
