package oncall.domain.date;

public class Month {

    // - [ ]  문자가 아닌 경우
    //- [ ]  1~12 사이의 숫자가 아닌 경우
    private final MonthType month;
    // - [ ]  월~금 사이의 문자가 아닌 경우
    //- [ ]  해당 달의 시작 요일이 아닌 경우
    private final DayType dayType; // 시작요일

    public Month(final int month, final String day) {
        this.month = MonthType.from(month);
        this.dayType = DayType.from(day);
    }

    public MonthType getMonth() {
        return month;
    }

    public DayType getDayType() {
        return dayType;
    }
}
