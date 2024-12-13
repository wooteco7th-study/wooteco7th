package christmas.dto;

public record DiscountDetailsDto(int christmasDday, int dayDiscount, int specialDiscount, int giftEvent,
                                 boolean isWeekday) {
// 크리스마스 디데이 할인: -1,200원
//평일 할인: -4,046원
//특별 할인: -1,000원
//증정 이벤트: -25,000원

    public boolean isEmpty() {
        return christmasDday + dayDiscount + specialDiscount + giftEvent == 0;
    }
}
