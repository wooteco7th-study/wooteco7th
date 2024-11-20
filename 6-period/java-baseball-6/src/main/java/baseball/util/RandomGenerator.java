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
        while (hasNotEnoughSize(pickNumbers)) {
            int number = pickNumber();
            if (!doesContains(pickNumbers, number)) {
                pickNumbers.add(number);
            }
        }
        return pickNumbers;
    }

    private static boolean hasNotEnoughSize(final List<Integer> pickNumbers) {
        return pickNumbers.stream().distinct().count() != SIZE;
    }

    private static int pickNumber() {
        return Randoms.pickNumberInRange(MIN_NUMBER, MAX_NUMBER);
    }

    private static boolean doesContains(final List<Integer> pickNumbers, final int number) {
        return pickNumbers.contains(number);
    }
}
