package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;

public class RandomCoinGenerator {

    private static final List<Integer> numbers = Arrays.asList(500, 100, 50, 10);

    private RandomCoinGenerator() {

    }

    public static Coin generate() {
        final int number = Randoms.pickNumberInList(numbers);
        return Coin.findByAmount(number);
    }
}
