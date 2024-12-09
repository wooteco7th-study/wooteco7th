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

    public DayOfWeek getNextDayOfWeek() {
        return DayOfWeek.getNext(dayOfWeek);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Month getMonth() {
        return month;
    }
}
