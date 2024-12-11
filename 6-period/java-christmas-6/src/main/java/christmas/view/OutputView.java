package christmas.view;

import christmas.dto.BenefitMenuReceipt;
import christmas.dto.DiscountReceipt;
import christmas.dto.OrderMenuReceipt;
import java.util.List;

public interface OutputView {

    void printIntro();

    void printAskVisitDay();

    void printAskOrderMenu();

    void printOrderMenuReceipts(final List<OrderMenuReceipt> orderMenuReceipts);

    void printBeforeDiscountTotalPrice(final long totalPrice);

    void printBenefitMenuReceipt(final BenefitMenuReceipt benefitMenuReceipt);

    void printDiscountReceipts(final List<DiscountReceipt> discountReceipts);

    void printTotalDiscount(final int totalDiscount);

    void printAfterDiscountTotalPrice(final long totalPrice);

    void printBadgeName(final String name);
}
