package vendingmachine.error;

public enum ErrorMessage {

    INVALID_MONEY_FORMAT("금액은 숫자여야 합니다."),
    INVALID_PRODUCT_FORMAT("상품 형식이 올바르지 않습니다."),
    INVALID_DUPLICATED_PRODUCT("상품은 중복될 수 없습니다."),
    INVALID_PRODUCT_PRICE("상품 가격은 100원 이상 10원 단위여야 합니다."),
    INVALID_PRODUCT_QUANTITY("상품 수량은 1개이상이여야 합니다."),
    INVALID_CAN_NOT_PURCHASE_PRODUCT("구매할 수 없는 상품 입니다."),
    INVALID_EXCEEDS_MONEY_RANGE("금액은 자연수여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
