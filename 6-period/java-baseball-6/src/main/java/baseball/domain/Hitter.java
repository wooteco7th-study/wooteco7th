package baseball.domain;

import java.util.List;

public class Hitter {

    private static final Integer MIN_RANDOM_NUMBER = 1;
    private static final Integer MAX_RANDOM_NUMBER = 9;
    private static final Integer BALL_SIZE = 3;

    private final List<Integer> hitterBalls;

    public Hitter(List<Integer> hitterBalls) {
        validate(hitterBalls);
        this.hitterBalls = hitterBalls;
    }

    private void validate(List<Integer> hitterBalls) {
        validateSize(hitterBalls);
        validateRange(hitterBalls);
        validateDuplication(hitterBalls);
    }

    private void validateSize(List<Integer> hitterBalls) {
        if (hitterBalls.size() != BALL_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateRange(List<Integer> hitterBalls) {
        hitterBalls.forEach(this::checkRange);
    }

    private void checkRange(Integer hitterBall) {
        if (hitterBall < MIN_RANDOM_NUMBER || hitterBall > MAX_RANDOM_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<Integer> hitterBalls) {
        if (checkDuplication(hitterBalls)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkDuplication(List<Integer> hitterBalls) {
        return hitterBalls.stream().distinct().toList().size() != hitterBalls.size();
    }

    public List<Integer> getHitterBalls() {
        return hitterBalls;
    }
}
