package baseball.util;

import baseball.domain.BaseballRules;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

    public static List<Integer> makeRandomNumbers() {
        List<Integer> pickNumbers = new ArrayList<>();
        while (pickNumbers.stream().distinct().count() != BaseballRules.SIZE.getValue()) {
            pickNumbers.add(Randoms.pickNumberInRange(BaseballRules.MIN_NUMBER.getValue(),
                    BaseballRules.MAX_NUMBER.getValue()));
        }
        return pickNumbers;
    }
}
