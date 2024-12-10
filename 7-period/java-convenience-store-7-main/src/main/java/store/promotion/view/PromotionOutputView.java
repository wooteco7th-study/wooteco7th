package store.promotion.view;

import store.promotion.dto.NonPromotionCheckResult;
import store.promotion.dto.PromotionCheckResult;

public class PromotionOutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_PROMOTION = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String ASK_NON_PROMOTION = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";

    public void printPromotionCheckResult(final PromotionCheckResult promotionCheckResult) {
        printlnMessage(LINE_SEPARATOR + String.format(ASK_PROMOTION, promotionCheckResult.name(),
                promotionCheckResult.quantity()));
    }

    public void printNonPromotionCheckResult(final NonPromotionCheckResult nonPromotionCheckResult) {
        printlnMessage(LINE_SEPARATOR + String.format(ASK_NON_PROMOTION, nonPromotionCheckResult.name(),
                nonPromotionCheckResult.normal() + nonPromotionCheckResult.promotion()));
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
