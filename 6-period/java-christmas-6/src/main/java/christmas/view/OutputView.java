package christmas.view;

import christmas.dto.DiscountReceiptGroup.DiscountReceipt;
import christmas.dto.OrderMenuReceiptGroup.OrderMenuReceipt;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MENUS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PREVIEW_HEADER = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String TOTAL_PRICE_HEADER = "<할인 전 총주문 금액>";
    private static final String BENEFIT_HEADER = "<증정 메뉴>";
    private static final String DISCOUNT_RECEIPT_HEADER = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_HEADER = "<총혜택 금액>";
    private static final String TOTAL_PRICE_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String PRICE = "%,d원";
    private static final String DISCOUNT_FORMAT = "%s-%,d원";
    private static final String NONE = "없음";


    public void printIntro() {
        printlnMessage(INTRO);
    }

    public void printAskVisitDate() {
        printlnMessage(ASK_VISIT_DATE);
    }

    public void printAskOrderMenus() {
        printlnMessage(ASK_ORDER_MENUS);
    }

    public void printPreview() {
        printlnMessage(PREVIEW_HEADER);
    }

    public void printOrderMenus(final List<OrderMenuReceipt> orderMenuReceipts) {
        final String message = orderMenuReceipts.stream()
                .map(orderMenuReceipt -> String.format(MENU_FORMAT, orderMenuReceipt.name(),
                        orderMenuReceipt.quantity()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + ORDER_MENU_HEADER + LINE_SEPARATOR + message);
    }

    public void printTotalPriceBeforeDiscount(final int totalPrice) {
        final String message = String.format(PRICE, totalPrice);
        printlnMessage(LINE_SEPARATOR + TOTAL_PRICE_HEADER + LINE_SEPARATOR + message);
    }

    public void printBenefitMenu(final String menu, final int quantity) {
        String message = String.format(MENU_FORMAT, menu, quantity);
        if (quantity <= 0) {
            message = NONE;
        }
        printlnMessage(LINE_SEPARATOR + BENEFIT_HEADER + LINE_SEPARATOR + message);
    }

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

    public void printTotalDiscount(final int totalDiscount) {
        final String message = String.format(PRICE, -totalDiscount);
        printlnMessage(LINE_SEPARATOR + TOTAL_DISCOUNT_HEADER + LINE_SEPARATOR + message);
    }

    public void printTotalPriceAfterDiscount(final int totalPrice) {
        final String message = String.format(PRICE, totalPrice);
        printlnMessage(LINE_SEPARATOR + TOTAL_PRICE_AFTER_DISCOUNT + LINE_SEPARATOR + message);
    }

    public void printBadge(final String badge) {
        printlnMessage(LINE_SEPARATOR + BADGE_HEADER + LINE_SEPARATOR + badge);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
