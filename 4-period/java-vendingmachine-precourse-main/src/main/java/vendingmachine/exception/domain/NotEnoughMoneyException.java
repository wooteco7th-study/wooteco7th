package vendingmachine.exception.domain;

public class NotEnoughMoneyException extends IllegalArgumentException {
    public NotEnoughMoneyException() {
        super("[ERROR] 자판기의 잔액이 부족합니다.");
    }
}
