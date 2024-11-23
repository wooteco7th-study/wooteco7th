package christmas.domain.vo;

import java.time.LocalDate;

public class VisitDate {

    private final LocalDate visitDate;

    public VisitDate(final LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }
}
