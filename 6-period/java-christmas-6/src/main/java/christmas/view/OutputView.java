package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.EventBadge;
import christmas.domain.OrderForm;

import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String START_MSG = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEW_LINE;
    private static final String ORDER_MENU = NEW_LINE + "<주문 메뉴>";
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT = NEW_LINE + "<할인 전 총주문 금액>";
    private static final String FREE_GIFT = NEW_LINE + "<증정 메뉴>";
    private static final String BENEFIT_LIST = NEW_LINE + "<혜택 내역>";
    private static final String BENEFIT_AMOUNT = NEW_LINE + "<총혜택 금액>";
    private static final String EXPECTED_PAY_AMOUNT = NEW_LINE + "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE = NEW_LINE + "<12월 이벤트 배지>";
    private static final String ORDER_MENU_FORMAT = "%s %d개" + NEW_LINE;
    private static final String MONEY_FORMAT = "%,d원" + NEW_LINE;
    private static final String DISCOUNT_MONEY_FORMAT = "%s: -%,d원";
    private static final String BENEFIT_AMOUNT_FORMAT = "-%,d원";
    private static final String EXPECTED_PAY_AMOUNT_FORMAT = "%,d원";
    private static final String NONE = "없음";


    public void printError(String error) {
        System.out.println(error);
    }

    public void printReceipt(final OrderForm orderForm, final Benefit benefit) {
        System.out.printf(START_MSG, orderForm.getOrderDate().getDate());
        printOrderMenu(orderForm);
        printTotalPriceBeforeDiscount(orderForm);
        printGiftMenu(benefit);
        printBenefit(benefit);
        printBenefitAmount(benefit);
        printExpectedPayAmount(orderForm, benefit);
        printBadge(benefit);
    }

    private void printBadge(final Benefit benefit) {
        System.out.println(EVENT_BADGE);
        EventBadge eventBadge = EventBadge.from(benefit.getTotalBenefitAmount());
        System.out.println(eventBadge.getName());
    }

    private void printExpectedPayAmount(final OrderForm orderForm, final Benefit benefit) {
        System.out.println(EXPECTED_PAY_AMOUNT);
        System.out.printf(EXPECTED_PAY_AMOUNT_FORMAT + NEW_LINE, orderForm.getTotalPriceBeforeDiscount() - benefit.getBenefitDiscount());
    }

    private void printBenefitAmount(final Benefit benefit) {
        System.out.println(BENEFIT_AMOUNT);
        int totalBenefitAmount = benefit.getTotalBenefitAmount();
        if (totalBenefitAmount > 0) {
            System.out.printf(BENEFIT_AMOUNT_FORMAT + NEW_LINE, totalBenefitAmount);
        }
        if (totalBenefitAmount <= 0) {
            System.out.println(NONE);
        }
    }

    private void printBenefit(final Benefit benefit) {
        Map<String, Integer> discountList = benefit.discountList();
        System.out.println(BENEFIT_LIST);
        if (discountList.isEmpty()) {
            System.out.println(NONE);
        }
        if (!discountList.isEmpty()) {
            discountList.forEach((key, value) ->
                    System.out.printf(DISCOUNT_MONEY_FORMAT, key, value));
        }
    }

    private void printGiftMenu(final Benefit benefit) {
        System.out.println(FREE_GIFT);
        if (benefit.hasFreeGift()) {
            System.out.println("샴페인 1개" + NEW_LINE);
        }
        if (!benefit.hasFreeGift()) {
            System.out.println(NONE);
        }
    }

    private void printTotalPriceBeforeDiscount(final OrderForm orderForm) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT);
        System.out.printf(MONEY_FORMAT, orderForm.getTotalPriceBeforeDiscount());
    }

    private void printOrderMenu(final OrderForm orderForm) {
        System.out.println(ORDER_MENU);
        orderForm.getMenus()
                .forEach(menu -> System.out.printf(ORDER_MENU_FORMAT, menu.getMenu(), menu.getQuantity()));
    }
}
