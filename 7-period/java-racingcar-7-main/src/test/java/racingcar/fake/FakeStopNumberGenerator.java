package racingcar.fake;

import racingcar.domain.NumberGenerator;

public class FakeStopNumberGenerator implements NumberGenerator {

    @Override
    public int generate() {
        return 0;
    }
}
