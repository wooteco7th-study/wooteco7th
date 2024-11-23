package christmas.view.console;

import christmas.dto.BenefitMenuReceipt;
import christmas.dto.DiscountReceipt;
import christmas.dto.OrderMenuReceipt;
import christmas.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EVENT_PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String BEFORE_DISCOUNT_TOTAL_PRICE_HEADER = "<할인 전 총주문 금액>";
    private static final String BENEFIT_MENU_HEADER = "<증정 메뉴>";
    private static final String DISCOUNT_RECEIPT_HEADER = "<혜택 내역>";
    private static final String DISCOUNT_FORMAT = "%s: -%,d원";
    private static final String TOTAL_DISCOUNT_HEADER = "<총혜택 금액>";
    private static final String TOTAL_PRICE_FORMAT = "%,d원";
    private static final String AFTER_DISCOUNT_TOTAL_PRICE_HEADER = "<할인 후 예상 결제 금액>";
    private static final String BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String NONE = "없음";

    @Override
    public void printIntro() {
        printlnMessage(INTRO);
    }

    @Override
    public void printAskVisitDay() {
        printlnMessage(ASK_VISIT_DAY);
    }

    @Override
    public void printAskOrderMenu() {
        printlnMessage(ASK_ORDER_MENU);
    }

    @Override
    public void printOrderMenuReceipts(final List<OrderMenuReceipt> orderMenuReceipts) {
        printlnMessage(EVENT_PREVIEW);
        final String message = orderMenuReceipts.stream()
                .map(orderMenuReceipt -> String.format(MENU_FORMAT, orderMenuReceipt.name(),
                        orderMenuReceipt.quantity()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + ORDER_MENU_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printBeforeDiscountTotalPrice(final long totalPrice) {
        final String message = String.format(TOTAL_PRICE_FORMAT, totalPrice);
        printlnMessage(LINE_SEPARATOR + BEFORE_DISCOUNT_TOTAL_PRICE_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printBenefitMenuReceipt(final BenefitMenuReceipt benefitMenuReceipt) {
        String message = String.format(MENU_FORMAT, benefitMenuReceipt.name(), benefitMenuReceipt.quantity());
        if (benefitMenuReceipt.quantity() == 0) {
            message = NONE;
        }
        printlnMessage(LINE_SEPARATOR + BENEFIT_MENU_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printDiscountReceipts(final List<DiscountReceipt> discountReceipts) {
        String message = discountReceipts.stream()
                .map(discountReceipt -> String.format(DISCOUNT_FORMAT, discountReceipt.name(),
                        discountReceipt.discount()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        if (discountReceipts.isEmpty()) {
            message = NONE;
        }
        printlnMessage(LINE_SEPARATOR + DISCOUNT_RECEIPT_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printTotalDiscount(final int totalDiscount) {
        final String message = String.format(TOTAL_PRICE_FORMAT, -totalDiscount);
        printlnMessage(LINE_SEPARATOR + TOTAL_DISCOUNT_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printAfterDiscountTotalPrice(final long totalPrice) {
        final String message = String.format(TOTAL_PRICE_FORMAT, totalPrice);
        printlnMessage(LINE_SEPARATOR + AFTER_DISCOUNT_TOTAL_PRICE_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printBadgeName(final String name) {
        printlnMessage(LINE_SEPARATOR + BADGE_HEADER + LINE_SEPARATOR + name);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }
    
}
