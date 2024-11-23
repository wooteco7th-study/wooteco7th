package christmas.domain.discount;

import christmas.domain.OrderForm;
import christmas.domain.VisitDate;

public interface DiscountStrategy {

    boolean isApplicable(VisitDate visitDate);

    int appliedAmount(OrderForm orderForm);

    String getName();
}
