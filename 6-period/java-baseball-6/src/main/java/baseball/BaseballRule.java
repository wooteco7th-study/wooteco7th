package baseball;

public enum BaseballRule {

    BASEBALL_SIZE(3);
    private final int value;

    BaseballRule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
