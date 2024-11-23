package christmas.rule;

public enum ChristmasRule {

    START_DAY(1),
    END_DAY(2);

    private final int value;

    ChristmasRule(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
