package baseball.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private final List<Integer> balls = new ArrayList<>();

    public Computer() {
        init();
    }

    private void init() {
        while (balls.size() < 3) {
            int randomNumber = createRandomNumber();
            addRandomNumber(randomNumber);
        }
    }

    private int createRandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
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
