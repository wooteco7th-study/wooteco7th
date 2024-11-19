package baseball.domain;

public enum BaseballRules {

    SIZE(3),
    MIN_NUMBER(1),
    MAX_NUMBER(9);

    private final int value;

    BaseballRules(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
