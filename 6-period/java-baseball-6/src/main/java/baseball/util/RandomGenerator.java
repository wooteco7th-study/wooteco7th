package baseball.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class RandomGenerator {

    public static Set<Integer> makeRandomNumbers() {
        Set<Integer> pickNumbers = new HashSet<>();
        while (pickNumbers.size() != 3) {
            pickNumbers.add(Randoms.pickNumberInRange(1, 9));
        }
        return pickNumbers;
    }
}
