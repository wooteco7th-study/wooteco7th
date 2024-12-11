package baseball.util;

import static baseball.domain.baseball.BaseballNumber.MAX_NUMBER;
import static baseball.domain.baseball.BaseballNumber.MIN_NUMBER;
import static baseball.domain.baseball.BaseballNumbers.SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

    public static List<Integer> makeRandomNumbers() {
        List<Integer> pickNumbers = new ArrayList<>();
        while (pickNumbers.size() < SIZE) {
            int number = pickNumber();
            if (!pickNumbers.contains(number)) {
                pickNumbers.add(number);
            }
        }
        return pickNumbers;
    }

    private static int pickNumber() {
        return Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
    }
}
