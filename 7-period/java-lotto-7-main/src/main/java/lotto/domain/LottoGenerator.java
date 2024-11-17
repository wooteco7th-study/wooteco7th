package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoGenerator {

    public static Lotto generate(final int min, final int max, final int count) {
        final List<Integer> numbers = Randoms.pickUniqueNumbersInRange(min, max, count);
        return new Lotto(numbers);
    }
}
