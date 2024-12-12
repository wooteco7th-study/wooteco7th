package vendingmachine.view;

public enum PrintMessage {

    INPUT_COIN("자판기가 보유하고 있는 금액을 입력해 주세요."),
    HAVING_COIN("자판기가 보유한 동전"),
    INPUT_PRODUCT_INFO("상품명과 가격, 수량을 입력해 주세요."),
    INPUT_MONEY("투입 금액을 입력해 주세요."),
    INPUT_PRODUCT_NAME("구매할 상품명을 입력해 주세요."),
    RETURN_CHANGE("잔돈");

    private final String message;

    PrintMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
