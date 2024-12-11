package racingcar.domain;

public class Car {

    private static final int FORWARD_CONDITION = 4;

    private final NumberGenerator randomNumberGenerator;
    private final CarName carName;
    private int score;

    public Car(final NumberGenerator randomNumberGenerator, final CarName carName, final int score) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.carName = carName;
        this.score = score;
    }

    public boolean isMatchedScore(final int value) {
        return score == value;
    }

    public void move() {
        final int number = randomNumberGenerator.generate();
        if (number >= FORWARD_CONDITION) {
            score++;
        }
    }

    public String getName() {
        return carName.getName();
    }

    public int getScore() {
        return score;
    }
}
