package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class QuickLottoGenerator {

    private QuickLottoGenerator() {
    }

    public static Lotto generate(final int min, final int max, final int size) {
        final List<Integer> numbers = Randoms.pickUniqueNumbersInRange(min, max, size);
        return new Lotto(numbers);
    }
}
