package christmas.domain;

import christmas.domain.Menu.MenuType;
import java.math.BigDecimal;
import java.util.Optional;

public class PromotionProcessor {

    private final Day visitDay;
    private final Orders orders;

    public PromotionProcessor(final Day visitDay, final Orders orders) {
        this.visitDay = visitDay;
        this.orders = orders;
    }

    public boolean checkAtLeastPrice() {
        return orders.calculateTotalPrice().compareTo(new BigDecimal(100000)) >= 0;
    }

    public BigDecimal checkUntiChristmasDiscount() {
        BigDecimal discount = BigDecimal.valueOf(1000);
        BigDecimal untilChristmas = new BigDecimal(100).multiply(new BigDecimal(visitDay.diffFromFirstDay()));
        return discount.add(untilChristmas);
    }

    public BigDecimal checkDayDiscount() {
        if (visitDay.isWeekend()) {
            return calculateMainMenuDiscount();
        }
        return calculateDessertMenu();
    }

    private BigDecimal calculateMainMenuDiscount() {
        return new BigDecimal(2023).multiply(new BigDecimal(orders.countSameTypeMenu(MenuType.MAIN)));
    }

    private BigDecimal calculateDessertMenu() {
        return new BigDecimal(2023).multiply(new BigDecimal(orders.countSameTypeMenu(MenuType.DESSERT)));
    }

    public BigDecimal checkSpecialDiscount() {
        if (visitDay.isSpecialDay()) {
            return BigDecimal.valueOf(1000);
        }
        return BigDecimal.ZERO;
    }

    public Optional<Order> checkGift() {
        if (orders.calculateTotalPrice().compareTo(new BigDecimal("120000")) >= 0) {
            return Optional.of(new Order(Menu.샴페인, new Quantity(1)));
        }
        return Optional.empty();
    }

    public BigDecimal calculateExpectPrice(BigDecimal totalDiscountPrice) {
        return orders.calculateTotalPrice().subtract(totalDiscountPrice);
    }

//## ✅ 총 혜택 계산하기
//
//- 날짜와 메뉴, 총 주문금액별 혜택을 계산한다.
//- 총혜택 금액 : 할인 금액의 합계 + 증정 메뉴의 가격
//- 할인 후 예상 결제 금액 : 할인 전 총주문 금액 - 할인 금액
//
//> 증정 메뉴의 가격은 포함하지 않는다.
//
//## ✅ 총 혜택 출력하기
//
//- 할인 전 총주문 금액 보여주기
//- 증정 메뉴 보여주기
//    - 증정 이벤트에 해당하지 않는 경우, "없음"
//            - 혜택 내역 보여주기
//    - 고객에게 적용된 이벤트 내역만 보여주기
//    - 적용된 이벤트가 하나도 없다면 "없음"
//            - 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력
//- 총혜택 금액 보여주기
//    - 할인 금액이 없다면 "0원"
//            - 할인 후 예상 결제 금액 보여주기
//- 12월 이벤트 배지 보여주기
//    - 이벤트 배지가 부여되지 않는 경우, "없음"
}
