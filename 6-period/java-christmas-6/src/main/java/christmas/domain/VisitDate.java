package christmas.domain;

import static christmas.exception.ExceptionMessage.INVALID_VISIT_DATE;

public class VisitDate {

    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 31;
    private final int date;

    public VisitDate(final int date) {
        validateRange(date);
        this.date = date;
    }

    private void validateRange(final int date) {
        if (date < MIN_RANGE || date > MAX_RANGE) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE.getMessage());
        }
    }

    public int getDate() {
        return date;
    }
}
