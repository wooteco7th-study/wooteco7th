package vendingmachine.exception;

public enum ErrorMessage {
    INVALID_PARSE_TO_INT("[ERROR] 숫자로 변환할 수 없는 값이 포함되어 있습니다:"),
    INVALID_REQUEST_AMOUNT("올바른 금액을 입력해주세요."),
    INVALID_REQUEST_AMOUNT_UNIT("금액은 10원 단위여야 합니다."),
    INVALID_REQUEST_AMOUNT_RANGE("금액은 0 이상이어야 합니다."),

    INVALID_REQUEST_PRODUCT_FORM("올바른 상품 정보를 입력해주세요. [상품명,가격,수량] 형식이어야 합니다."),
    INVALID_REQUEST_PRODUCT_NAME("상품명은 비어있을 수 없습니다."),

    INVALID_PRODUCT_PRICE("상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 합니다"),
    INVALID_PRODUCT_QUANTITY("상품 수량은 0 이상만 가능합니다"),

    INVALID_PRODUCT_DECREASE("솔드 아웃 입니다."),
    INVALID_PRODUCT_NAME_EMPTY("상품명은 비어있을 수 없습니다."),

    INVALID_PRODUCT_DUPLICATE("상품명은 중복할 수 없습니다."),
    INVALID_PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다");

    public final String message;

    ErrorMessage(final String message) {
        this.message = String.format("[ERROR]" + message);
    }

    public String getMessage() {
        return message;
    }
}
