package oncall.domain.date;

import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Month {

    private static final int MIN = 1;
    private static final int MAX = 12;
    // - [ ]  문자가 아닌 경우
    //- [ ]  1~12 사이의 숫자가 아닌 경우
    private final int month;
    // - [ ]  월~금 사이의 문자가 아닌 경우
    //- [ ]  해당 달의 시작 요일이 아닌 경우
    private final DayType dayType; // 시작요일

    public Month(final int month, final String day) {
        validate(month);
        this.month = month;
        this.dayType = DayType.from(day);
    }

    private void validate(final int value) {
        if (value < MIN || value > MAX) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }
}
