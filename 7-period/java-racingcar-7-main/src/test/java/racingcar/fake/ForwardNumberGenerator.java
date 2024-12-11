package racingcar.fake;

import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;

public class ForwardNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return RandomNumberGenerator.MAX;
    }
}
