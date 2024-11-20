package racingcar.domain;

public class Score {

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score updateValue(final int value) {
        return new Score(value);
    }

    public boolean isSameValue(final int value) {
        return this.value == value;
    }

    public int getValue() {
        return value;
    }
}
