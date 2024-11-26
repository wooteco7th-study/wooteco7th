package vendingmachine;

public enum ErrorMessage {
    INVALID_PARSE_TO_INT("[ERROR] 숫자로 변환할 수 없는 값이 포함되어 있습니다:"),
    INVALID_REQUEST_AMOUNT("올바른 금액을 입력해주세요."),
    INVALID_REQUEST_AMOUNT_UNIT("금액은 10원 단위여야 합니다."),
    INVALID_REQUEST_AMOUNT_RANGE("금액은 0 이상이어야 합니다."),

    INVALID_REQUEST_PRODUCT_FORM("올바른 상품 정보를 입력해주세요. [상품명,가격,수량] 형식이어야 합니다."),
    INVALID_REQUEST_PRODUCT_NAME("상품명은 비어있을 수 없습니다.");


    public final String message;

    ErrorMessage(final String message) {
        this.message = String.format("[ERROR]" + message);
    }

    public String getMessage() {
        return message;
    }
}
