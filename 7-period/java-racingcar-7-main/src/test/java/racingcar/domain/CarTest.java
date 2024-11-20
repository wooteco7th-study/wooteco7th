package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fake.FakeForwardNumberGenerator;
import racingcar.fake.FakeStopNumberGenerator;

class CarTest {

    private FakeForwardNumberGenerator fakeForwardNumberGenerator;
    private FakeStopNumberGenerator fakeStopNumberGenerator;

    @BeforeEach
    void setUp() {
        fakeForwardNumberGenerator = new FakeForwardNumberGenerator();
        fakeStopNumberGenerator = new FakeStopNumberGenerator();
    }

    @Test
    @DisplayName("자동차가 전진 한다.")
    void moveForwardTest() throws Exception {
        //given
        final CarName carName = new CarName("자동차다");
        final Score score = new Score(0);
        final Car car = new Car(carName, score, fakeForwardNumberGenerator);
        //when
        car.move();
        //then
        assertThat(car.getScore()).isEqualTo(score.getValue() + 1);

    }

    @Test
    @DisplayName("자동차가 정지 한다.")
    void moveStopTest() throws Exception {
        //given
        final CarName carName = new CarName("자동차다");
        final Score score = new Score(0);
        final Car car = new Car(carName, score, fakeStopNumberGenerator);
        //when
        car.move();
        //then
        assertThat(car.getScore()).isEqualTo(score.getValue());
    }
}
