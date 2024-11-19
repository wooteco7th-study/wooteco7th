package baseball;

public enum BaseballRule {

    BASEBALL_SIZE(3),
    MIN_NUMBER(1),
    MAX_NUMBER(3);
    private final int value;

    BaseballRule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
