package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator{
    @Override
    public int generate(final int min, final int max) {
        return Randoms.pickNumberInRange(min, max);
    }
}
