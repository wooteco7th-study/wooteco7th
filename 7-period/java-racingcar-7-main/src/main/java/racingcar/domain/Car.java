package racingcar.domain;

public class Car {

    private static final int GO_FORWARD_CONDITION = 4;
    private final CarName name;
    private final NumberGenerator numberGenerator;
    private Score score;

    public Car(final CarName name, final Score score, final NumberGenerator numberGenerator) {
        this.name = name;
        this.score = score;
        this.numberGenerator = numberGenerator;
    }

    public void move() {
        final int number = numberGenerator.generate();
        if (number >= GO_FORWARD_CONDITION) {
            this.score = score.updateValue(score.getValue() + 1);
        }
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        return score.getValue();
    }

    public boolean isSameScore(final int score) {
        return this.score.isSameValue(score);
    }
}
