package store.exception;

public enum ErrorMessage {

    INVALID_FILE_READ("파일을 읽는데 실패했습니다"),
    INVALID_FILE_FORMAT("유효한 파일 형식이 아닙니다"),

    INVALID_ORDER_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_PRODUCT_NAME("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVALID_QUANTITY_OUT_OF_STOCK("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),

    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
