package vendingmachine.exception.domain;

public class InvalidInitialAmountException extends IllegalArgumentException {
    public InvalidInitialAmountException() {
        super("[ERROR] 초기 금액은 0 이상이며 10원 단위여야 합니다.");
    }
}
