package christmas.Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class VisitDate {

    //방문 날짜 validate
    //방문 요일 계산
    private final int visitDate;

    public VisitDate(int visitDate) {
        validate(visitDate);
        this.visitDate = visitDate;
    }

    private void validate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException();
        }
    }

    public String calculateDate() {
        LocalDate today = LocalDate.of(2023, 12, visitDate);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US);
    }

    public int getVisitDate() {
        return visitDate;
    }
}
