package christmas.view;

import christmas.domain.Benefit;
import christmas.domain.OrderForm;
import christmas.domain.discount.DiscountStrategy;

import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String START_MSG = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEW_LINE;
    private static final String ORDER_MENU = NEW_LINE + "<주문 메뉴>";
    private static final String ORDER_MENU_FORMAT = "%s %d개" + NEW_LINE;
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT = NEW_LINE + "<할인 전 총주문 금액>";
    private static final String FREE_GIFT = NEW_LINE + "<증정 메뉴>";
    public static final String MONEY_FORMAT = "%,d원" + NEW_LINE;
    public static final String DISCOUNT_MONEY_FORMAT = "%s: -%,d원";
    public static final String BENEFIT_AMOUNT = NEW_LINE + "<총혜택 금액>";


    public void printError(String error) {
        System.out.println(error);
    }

    public void printReceipt(final OrderForm orderForm, final Benefit benefit) {
        System.out.printf(START_MSG, orderForm.getOrderDate().getDate());
        printOrderMenu(orderForm);
        printTotalPriceBeforeDiscount(orderForm);
        printGiftMenu(benefit);
        Map<DiscountStrategy, Integer> discountStrategyIntegerMap = benefit.discountList();
        discountStrategyIntegerMap.forEach((key, value) -> System.out.println(String.format(DISCOUNT_MONEY_FORMAT, key, value)));
        if (benefit.freeGiftAmount() != 0) {
            System.out.println("증정 이벤트: -25,000원");
        }
        System.out.println(BENEFIT_AMOUNT);

    }

    private void printGiftMenu(final Benefit benefit) {
        System.out.println(FREE_GIFT);
        if (benefit.freeGiftAmount() != 0) {
            System.out.println("샴페인 1개");
        }
        if (benefit.freeGiftAmount() == 0) {
            System.out.println("없음");
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
