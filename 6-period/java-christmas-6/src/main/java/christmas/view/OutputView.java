package christmas.view;

import christmas.domain.Discount;
import christmas.domain.DiscountInfo;
import christmas.domain.Promotion;
import christmas.domain.vo.OrderMenus;
import java.util.Map;

/**
 * 안녕하세요! 우테코 식당 12월 이벤트 플래너입니다. 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!) 3 주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g.
 * 해산물파스타-2,레드와인-1,초코케이크-1) 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1 12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 */
public class OutputView {
    private final String HELLO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final String REQUEST_VISIT_DATE_IN_DECEMBER = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String REQUEST_ORDER_MENU_AND_QUANTITY = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private final String DISCOUNT_BEFORE_TOTAL_MONEY_HEADER = "<할인 전 총주문 금액>";
    private final String PROMOTION_MENU_HEADER = "<증정 메뉴>";
    private final String DISCOUNT_RESULT_HEADER = "<혜택 내역>";
    private final String TOTAL_DISCOUNT_AMOUNT = "<총혜택 금액>";
    private final String FINAL_PRICE = "<할인 후 예상 결제 금액>";
    private final String BADGE_HEADER = "<12월 이벤트 배지>";


    public void printRequestVisitDateMessage() {
        System.out.println(HELLO + System.lineSeparator() + REQUEST_VISIT_DATE_IN_DECEMBER);
    }

    public void printRequestOrderMenusMessage() {
        System.out.println(REQUEST_ORDER_MENU_AND_QUANTITY);
    }

    public void printDiscountPreviewMessage(int visitDate) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate) + System.lineSeparator());
    }

    /**
     * <주문 메뉴> 티본스테이크 1개 바비큐립 1개 초코케이크 2개 제로콜라 1개
     * <p>
     * <할인 전 총주문 금액> 142,000원
     * <p>
     * <증정 메뉴> 샴페인 1개
     * <p>
     * <혜택 내역> 크리스마스 디데이 할인: -1,200원 평일 할인: -4,046원 특별 할인: -1,000원 증정 이벤트: -25,000원
     * <p>
     * <총혜택 금액> -31,246원
     * <p>
     * <할인 후 예상 결제 금액> 135,754원
     * <p>
     * <12월 이벤트 배지> 산타
     */
    public void printDiscountInfo(final DiscountInfo discountInfo) {
        System.out.println(ORDER_MENU_HEADER);

        OrderMenus orderMenus = discountInfo.getOrderMenus();
        orderMenus.getOrderMenus().entrySet()
                .forEach(i -> System.out.println(String.format("%s %d개", i.getKey(), i.getValue())));

        System.out.println();

        System.out.println(DISCOUNT_BEFORE_TOTAL_MONEY_HEADER);
        System.out.println(String.format("%,d원", discountInfo.getPriceBeforeDiscount()) + System.lineSeparator());

        System.out.println(PROMOTION_MENU_HEADER);
        Promotion promotion = discountInfo.getPromotion();
        if (!promotion.equals(Promotion.NONE)) {
            System.out.println(String.format("%s %d개", promotion.getPromotionName(), promotion.getGiveAmount())
                    + System.lineSeparator());
        }
        if (promotion.equals(Promotion.NONE)) {
            System.out.println(Promotion.NONE.getPromotionName() + System.lineSeparator());
        }

        System.out.println(DISCOUNT_RESULT_HEADER);
        if (discountInfo.getTotalDiscountAmount() == 0) {
            System.out.println("없음" + System.lineSeparator());
        }
        if (discountInfo.getTotalDiscountAmount() != 0) {
            Map<Discount, Integer> discountResults = discountInfo.getDiscountResults();
            discountResults.entrySet().forEach(
                    discount -> System.out.println(
                            String.format("%s: -%,d원", discount.getKey().getDiscountName(), discount.getValue())));
            int giveAmount = discountInfo.getPromotion().getGiveAmount();
            if (giveAmount != 0) {
                System.out.println(
                        String.format("증정 이벤트: -%,d원", discountInfo.getPromotion().getPrice()));
            }
        }
        System.out.println();

        System.out.println(TOTAL_DISCOUNT_AMOUNT);
        System.out.println(String.format("%,d원", discountInfo.getTotalDiscountAmount()) + System.lineSeparator());

        System.out.println(FINAL_PRICE);
        System.out.println(String.format("%,d원", discountInfo.getFinalPrice()) + System.lineSeparator());

        System.out.println(BADGE_HEADER);
        System.out.println(discountInfo.getBadge().getBadgeName());

    }
}
