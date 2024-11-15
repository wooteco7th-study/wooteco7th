package racingcar.fake;

import racingcar.domain.NumberGenerator;

public class FakeForwardNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 9;
    }
}
