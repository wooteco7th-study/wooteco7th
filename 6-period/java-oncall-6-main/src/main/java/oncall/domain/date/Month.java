package oncall.domain.date;

public class Month {

    private final MonthType monthType;
    private final DayType startDayType; // 시작 요일

    public Month(final MonthType monthType, final DayType startDayType) {
        this.monthType = monthType;
        this.startDayType = startDayType;
    }

    public int getEndDay() {
        return monthType.getEndDay();
    }

    public boolean isHoliday(int day) {
        return startDayType.isWeekend(day) || LegalHoliday.isLegalHoliday(monthType, day);
    }

    public boolean isSpecialDay(int day) {
        return !startDayType.isWeekend(day) && LegalHoliday.isLegalHoliday(monthType, day);
    }

    public int getMonthTypeValue() {
        return monthType.getValue();
    }

    public DayType getStartDayType() {
        return startDayType;
    }
}
