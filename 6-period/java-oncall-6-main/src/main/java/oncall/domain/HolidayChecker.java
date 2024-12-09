package oncall.domain;

import oncall.domain.date.DayType;
import oncall.domain.date.LegalHoliday;
import oncall.domain.date.Month;

public class HolidayChecker {

    // - 날짜를 입력받으면 평일인지 휴일인지 계산
    //- 평일이면서 법정공휴일의 경우 계산
    private final Month month;

    public HolidayChecker(final Month month) {
        this.month = month;
    }

    // 휴일 : 토요일, 일요일, 법정공휴일
    public boolean isHoliday(int inputDay) {
        return isSaturdayOrSunday(inputDay) || isLegalHoliday(inputDay);
    }

    public boolean isWeekdayHoliday(int inputDay) {
        return !isSaturdayOrSunday(inputDay) && isLegalHoliday(inputDay);
    }

    private boolean isLegalHoliday(int inputDay) {
        return LegalHoliday.isLegalHoliday(month.getMonth().getMonthNumber(), inputDay);
    }

    private boolean isSaturdayOrSunday(final int inputDay) {
        DayType startDay = month.getDayType();
        DayType day = startDay.passFrom(startDay, inputDay - 1);
        return day.isSaturdayOrSunday();
    }

    public DayType calculateDayType(int inputDay) {
        DayType startDay = month.getDayType();
        return startDay.passFrom(startDay, inputDay - 1);
    }

    public int getLastDayNumber() {
        return month.getMonth().getLastDayNumber();
    }

    public Month getMonth() {
        return month;
    }
}
