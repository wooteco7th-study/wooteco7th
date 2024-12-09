package oncall.domain;

import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class TurnScheduler {

    private final Turn weekdayTurn;
    private final Turn holidayTurn;

    public TurnScheduler(final Turn weekdayTurn, final Turn holidayTurn) {
        this.weekdayTurn = weekdayTurn;
        this.holidayTurn = holidayTurn;
        validateSize(weekdayTurn, holidayTurn);
    }

    private void validateSize(final Turn weekdayTurn, final Turn holidayTurn) {
        if (weekdayTurn.getSize() != holidayTurn.getSize()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }
}
