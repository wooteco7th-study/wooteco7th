package lotto.service;

import static lotto.constant.LottoRule.LOTTO_NUMBER_MAX;
import static lotto.constant.LottoRule.LOTTO_NUMBER_MIN;
import static lotto.constant.LottoRule.LOTTO_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator {
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_SIZE);
    }
}
