package vendingmachine.domain;

public class Money {

    private int value;

    public Money(final int value) {
        this.value = value;
    }

    public boolean isExceedsValue(final int value) {
        return this.value >= value;
    }

    public void subtractValue(final int value) {
        this.value -= value;
    }

    public int getValue() {
        return value;
    }
}
