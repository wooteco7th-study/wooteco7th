package christmas.view;

public enum PrintMessage {

    HELLO_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_VISIT_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_ORDERS_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_DETAIL_INFO_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_INFO_MESSAGE("<주문 메뉴>"),
    TOTAL_PRICE_INFO_MESSAGE("<할인 전 총주문 금액>"),
    GIVE_MENU_INFO_MESSAGE("<증정 메뉴>"),
    PROMOTION_INFO_MESSAGE("<혜택 내역>"),
    TOTAL_PROMOTION_INFO_MESSAGE("<총 혜택 금액>"),
    PAYMENT_INFO_MESSAGE("<할인 후 예상 결제 금액>"),
    BADGE_INFO_MESSAGE("<12월 이벤트 배지>"); //<>구조가 반복되니 이를 분리시키는 방법도 생각

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
