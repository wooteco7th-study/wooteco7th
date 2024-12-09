package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.date.DayType;
import oncall.dto.TurnDto;
import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class TurnScheduler {

    private final Turn weekdayTurn;
    private final Turn holidayTurn;
    private final HolidayChecker holidayChecker;

    public TurnScheduler(final Turn weekdayTurn, final Turn holidayTurn, final HolidayChecker holidayChecker) {
        this.weekdayTurn = weekdayTurn;
        this.holidayTurn = holidayTurn;
        validateSize(weekdayTurn, holidayTurn);
        this.holidayChecker = holidayChecker;
    }

    public List<TurnDto> makeWorkSchedule() {
        int lastDayNumber = holidayChecker.getLastDayNumber();
        List<TurnDto> result = new ArrayList<>();
        String pastName = "";
        for (int number = 1; number <= lastDayNumber; number++) {
            result.add(processPerPerson(number, pastName));
            pastName = result.getLast().name();
        }
        return result;
    }

    private TurnDto processPerPerson(final int number, final String pastName) {
        Name name = getName(number, pastName);
        DayType dayType = holidayChecker.calculateDayType(number);
        return makeTurnDto(number, dayType, name.getName());
    }

    private Name getName(final int number, final String pastName) {
        if (holidayChecker.isHoliday(number)) {
            return holidayTurn.getNextName(pastName);
        }
        return weekdayTurn.getNextName(pastName);
    }

    private TurnDto makeTurnDto(int day, DayType dayType, String name) {
        return new TurnDto(holidayChecker.getMonth().getMonth().getMonthNumber(), day, dayType.name(), name,
                holidayChecker.isWeekdayHoliday(day));
    }

    private void validateSize(final Turn weekdayTurn, final Turn holidayTurn) {
        if (weekdayTurn.getSize() != holidayTurn.getSize()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }
}
