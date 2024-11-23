package christmas.domain.vo;

import christmas.rule.ChristmasRule;
import christmas.rule.ErrorMessage;
import christmas.util.RangeValidator;
import christmas.view.InputRequestVO.Validator;
import java.time.LocalDate;

public class VisitDate {

    private final LocalDate visitDate;

    public VisitDate(final int visitDate) {
        Validator.check(!RangeValidator.isNumberInRange(visitDate,
                        ChristmasRule.START_DAY.getValue(),
                        ChristmasRule.END_DAY.getValue()))
                .withError(new IllegalArgumentException(ErrorMessage.INVALID_RANGE_DATE.getMessage()));
        this.visitDate = LocalDate.of(2023, 12, visitDate);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }
}
