package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomNumberGenerator {

    public static int generate(List<Integer> coins) {
        return Randoms.pickNumberInList(coins);
    }
}
