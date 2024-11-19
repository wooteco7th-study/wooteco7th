package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private static final Integer MIN_RANDOM_NUMBER = 1;
    private static final Integer MAX_RANDOM_NUMBER = 9;
    private static final Integer BALL_SIZE = 3;

    private final List<Integer> balls = new ArrayList<>();

    public Computer() {
        init();
    }

    private void init() {
        while (balls.size() < BALL_SIZE) {
            int randomNumber = createRandomNumber();
            addRandomNumber(randomNumber);
        }
    }

    private int createRandomNumber() {
        return Randoms.pickNumberInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER);
    }

    private void addRandomNumber(int randomNumber) {
        if (!balls.contains(randomNumber)) {
            balls.add(randomNumber);
        }
    }

    public List<Integer> getBalls() {
        return balls;
    }
}
