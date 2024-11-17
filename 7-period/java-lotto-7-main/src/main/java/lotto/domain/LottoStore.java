package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_TICKET_PRICE = 1_000;


    public static List<Lotto> generateLottos(final Money money) {
        final int count = money.calculateQuotient(LOTTO_TICKET_PRICE);
        return IntStream.range(0, count)
                .mapToObj(number -> createLotto())
                .toList();
    }

    private static Lotto createLotto() {
        final List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER,
                LOTTO_NUMBER_COUNT);
        return new Lotto(numbers);
    }

    private LottoStore() {

    }

}
