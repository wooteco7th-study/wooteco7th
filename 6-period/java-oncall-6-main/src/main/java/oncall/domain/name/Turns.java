package oncall.domain.name;

import oncall.exception.CustomIllegalArgumentException;
import oncall.exception.ErrorMessage;

public class Turns {

    private final Turn weekdayTurn;
    private final Turn holidayTurn;

    public Turns(final Turn weekdayTurn, final Turn holidayTurn) {
        validate(weekdayTurn, holidayTurn);
        this.weekdayTurn = weekdayTurn;
        this.holidayTurn = holidayTurn;
    }

    private void validate(final Turn weekdayTurn, final Turn holidayTurn) {
        validateSize(weekdayTurn, holidayTurn);
        validateFrequency(weekdayTurn, holidayTurn);
    }

    private void validateFrequency(final Turn weekdayTurn, final Turn holidayTurn) {
        if (isInvalidFrequency(weekdayTurn, holidayTurn)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private void validateSize(final Turn weekdayTurn, final Turn holidayTurn) {
        if (weekdayTurn.getSize() != holidayTurn.getSize()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    private boolean isInvalidFrequency(final Turn weekdayTurn, final Turn holidayTurn) {
        return weekdayTurn.getNames().stream()
                .anyMatch(name -> !holidayTurn.hasOnlyOne(name));
    }


}
