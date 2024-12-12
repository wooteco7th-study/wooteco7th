package oncall.domain;

public class Month {

    private final MonthType monthType;
    private final DayType dayType;

    public Month(final MonthType monthType, final DayType dayType) {
        this.monthType = monthType;
        this.dayType = dayType;
    }


}
