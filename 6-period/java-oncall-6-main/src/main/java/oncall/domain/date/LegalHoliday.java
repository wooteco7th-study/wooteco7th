package oncall.domain.date;

public enum LegalHoliday {

    신정(MonthType.from(1), 1), 삼일절(MonthType.from(3), 1), 어린이날(MonthType.from(5), 5),
    현충일(MonthType.from(6), 6), 광복절(MonthType.from(8), 15), 개천절(MonthType.from(10), 3),
    한글날(MonthType.from(10), 9), 성탄절(MonthType.from(12), 25);

    private final MonthType monthType;
    private final int day;

    LegalHoliday(final MonthType monthType, final int day) {
        this.monthType = monthType;
        this.day = day;
    }
}
