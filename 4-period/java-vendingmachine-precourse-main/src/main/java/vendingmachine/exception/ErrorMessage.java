package vendingmachine.exception;

public enum ErrorMessage {

    NULL("입력값은(는) null일 수 없습니다."),
    BLANK("입력값은(는) 빈 문자열이거나 공백일 수 없습니다."),
    EMPTY("입력값은(는) 비어있을 수 없습니다."),

    NOT_A_NUMBER("숫자만 입력할 수 있습니다."),

    INVALID_HOLDING_AMOUNT("유효하지 않은 보유 금액입니다. 다시 입력해 주세요."),
    INVALID_INPUT_AMOUNT("유효하지 않은 투입 금액입니다. 다시 입력해 주세요."),

    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    INVALID_PRICE_ZERO("0원일 수 없습니다."),
    INVALID_RANGE("유효하지 않은 범위입니다"),
    INVALID_UNIT("유효하지 않은 단위입니다"),

    INVALID_PRICE_UNDER_EQUAL_HUNDRED("100원 미만일 수 없습니다."),

    INVALID_PRICE_DIVIDE_BY_TEN("10원으로 나누어 떨어져야 합니다."),

    INVALID_PRICE_AMOUNT("금액은 음수일 수 없습니다."),

    COIN_NOT_EXIST("동전이 존재하지 않습니다"),

    INVALID_PRICE_SUBTRACT("금액을 뺄 수 없습니다."),

    INVALID_HOLDING_PRODUCT("보유 상품 입력 형식이 잘못되었습니다."),

    INVALID_ZERO_QUANTITY("수량은 음수일 수 없습니다."),

    INVALID_HOLDING_PRODUCT_QUANTITY("보유 상품 수량은 0개일 수 없습니다."),
    INVALID_PRODUCT_NAME("존재하는 상품이 아닙니다."),
    DUPLICATED_PRODUCT_NAME("중복 상품을 입력할 수 없습니다."),
    INVALID_ORDER_PRICE("투입 금액보다 비싼 상품을 구매할 수 없습니다."),
    OUT_OF_STOCK("해당 상품의 재고가 없습니다."),
    ALL_OUT_OF_STOCK("모든 상품의 재고가 없습니다."),

    INVALID_STATE_ORDER("구매할 수 없습니다."),
    NO_PRODUCT_EXIST("보유 상품이 존재하지 않습니다.");;

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
