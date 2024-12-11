package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator{

    public static final int MIN = 0;
    public static final int MAX = 9;

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(MIN, MAX);
    }
}
