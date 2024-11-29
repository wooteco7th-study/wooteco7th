package vendingmachine.exception;

public enum ErrorMessage {

    BLANK("입력값은(는) 빈 문자열이거나 공백일 수 없습니다."),

    INVALID_AMOUNT("유효하지 않은 금액입니다."),

    COIN_NOT_EXIST("동전이 존재하지 않습니다"),

    INVALID_PRODUCT_FORMAT("보유 상품 입력 형식이 잘못되었습니다."),
    INVALID_PRODUCT_QUANTITY("수량은 음수일 수 없습니다."),
    INVALID_PRODUCT_NAME("존재하는 상품이 아닙니다."),
    DUPLICATED_PRODUCT("중복 상품을 입력할 수 없습니다."),
    OUT_OF_STOCK("해당 상품의 재고가 없습니다."),
    NO_PRODUCT_EXIST("보유 상품이 존재하지 않습니다."),

    INVALID_ORDER_STATE("구매할 수 없습니다."),
    INSUFFICIENT_PRICE("투입 금액보다 비싼 상품을 구매할 수 없습니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
