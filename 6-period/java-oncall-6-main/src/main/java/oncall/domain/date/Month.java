package oncall.domain.date;

public class Month {

    private final MonthType month;
    private final DayType startDay;

    public Month(final int month, final String day) {
        this.month = MonthType.from(month);
        this.startDay = DayType.from(day);
    }

    public MonthType getMonth() {
        return month;
    }

    public DayType getStartDay() {
        return startDay;
    }
}
