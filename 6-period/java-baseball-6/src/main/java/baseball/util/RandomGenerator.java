package baseball.util;

import baseball.domain.baseball.BaseballRules;
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
        return pickNumbers.stream().distinct().count() != BaseballRules.SIZE.getValue();
    }

    private static int pickNumber() {
        return Randoms.pickNumberInRange(BaseballRules.MIN_NUMBER.getValue(),
                BaseballRules.MAX_NUMBER.getValue());
    }

    private static boolean doesContains(final List<Integer> pickNumbers, final int number) {
        return pickNumbers.contains(number);
    }
}
