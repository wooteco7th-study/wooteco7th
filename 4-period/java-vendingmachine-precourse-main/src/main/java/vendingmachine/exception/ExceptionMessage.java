package vendingmachine.exception;

import static java.lang.String.format;
import static vendingmachine.domain.Inventory.MINIMUM_STOCK;
import static vendingmachine.domain.Product.COIN_10_AMOUNT;
import static vendingmachine.domain.Product.MINIMUM_PRICE;

public enum ExceptionMessage {

    INPUT_BLANK("빈 값을 입력하셨습니다."),

    INVALID_NUMBER("유효하지 않은 숫자입니다."),

    INVALID_AMOUNT("금액은 숫자여야 합니다."),

    COIN_NOT_FOUND("해당 종류의 코인이 존재하지 않습니다."),

    INVALID_FORMAT("유효하지 않은 포맷입니다."),

    AMOUNT_MUST_BE_POSITIVE("금액은 양수여야 합니다."),

    PRODUCT_NOT_EXIST("존재하지 않는 상품입니다."),

    OUT_OF_STOCK("재고가 없습니다."),

    PRICE_OUT_OF_RANGE(
            format("상품 가격은 %d원 이상이어야 합니다.",
                    MINIMUM_PRICE)
    ),

    REMAINDER_EXIST(
            format("%d원으로 나누어 떨어져야 합니다.",
                    COIN_10_AMOUNT)
    ),

    STOCK_OUT_OF_RANGE(
            format("재고는 최소 %d개 이상이어야 합니다.",
                    MINIMUM_STOCK)
    ),
    ;

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public String getMessage() {
        return format(message);
    }
}
