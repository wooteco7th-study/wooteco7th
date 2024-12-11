package lotto.domain.generator;

import static lotto.domain.lotto.Lotto.LOTTO_SIZE;
import static lotto.domain.lotto.LottoNumber.END_INCLUSIVE;
import static lotto.domain.lotto.LottoNumber.START_INCLUSIVE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {

    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(START_INCLUSIVE, END_INCLUSIVE, LOTTO_SIZE);
    }
}
