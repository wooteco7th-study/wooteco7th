package christmas.Domain;

import christmas.Exception.InputException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public record VisitDate(int visitDate) {

    public VisitDate {
        validate(visitDate);
    }

    private void validate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new InputException();
        }
    }

    public String calculateDate() {
        LocalDate today = LocalDate.of(2023, 12, visitDate);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);
    }
}
