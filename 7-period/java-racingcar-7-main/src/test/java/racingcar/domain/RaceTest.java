package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fake.FakeForwardNumberGenerator;
import racingcar.fake.FakeStopNumberGenerator;

class RaceTest {

    private FakeForwardNumberGenerator fakeForwardNumberGenerator;
    private FakeStopNumberGenerator fakeStopNumberGenerator;

    @BeforeEach
    void setUp() {
        fakeForwardNumberGenerator = new FakeForwardNumberGenerator();
        fakeStopNumberGenerator = new FakeStopNumberGenerator();
    }

    @Test
    @DisplayName("전진만 하는 자동차가 우승자가 된다.")
    void getWinnersTest() throws Exception {
        //given
        final CarName carName1 = new CarName("전진차1");
        final Score score1 = new Score(0);
        final Car car1 = new Car(carName1, score1, fakeForwardNumberGenerator);

        final CarName carName2 = new CarName("전진차2");
        final Score score2 = new Score(0);
        final Car car2 = new Car(carName2, score2, fakeForwardNumberGenerator);

        final CarName carName3 = new CarName("정지차1");
        final Score score3 = new Score(0);
        final Car car3 = new Car(carName3, score3, fakeStopNumberGenerator);

        final CarName carName4 = new CarName("정지차2");
        final Score score4 = new Score(0);
        final Car car4 = new Car(carName4, score4, fakeStopNumberGenerator);

        final List<Car> cars = List.of(car1, car2, car3, car4);
        final Race race = new Race(cars);
        //when
        race.move();
        final List<String> winners = race.getWinners();

        //then
        assertThat(winners).contains(car1.getName(), car2.getName());

    }

}
