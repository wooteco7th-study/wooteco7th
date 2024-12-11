package racingcar.fake;

import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;

public class StopNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return RandomNumberGenerator.MIN;
    }
}
