package racingcar.domain;

public class Car {

    private final CarName name;
    private final Score score;

    public Car(final CarName name, final Score score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        return score.getValue();
    }

    public boolean isSameScore(final Score score) {
        return this.score.isSameValue(score.getValue());
    }
}
