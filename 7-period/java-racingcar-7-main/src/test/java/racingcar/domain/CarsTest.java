package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.fake.ForwardNumberGenerator;
import racingcar.fake.StopNumberGenerator;

class CarsTest {

    @Test
    void calculateMaxScoreTest() {
        //given
        final Car car1 = new Car(new ForwardNumberGenerator(), new CarName("밍트"), 0);
        final Car car2 = new Car(new StopNumberGenerator(), new CarName("수달"), 0);
        //when
        final Cars cars = new Cars(List.of(car1, car2));
        cars.moveAll();
        final int score = cars.calculateMaxScore();
        //then
        assertThat(score).isEqualTo(1);
    }

    @Test
    void getWinnersTest() {
        //given
        final Car car1 = new Car(new ForwardNumberGenerator(), new CarName("밍트"), 0);
        final Car car2 = new Car(new StopNumberGenerator(), new CarName("수달"), 0);
        //when
        final Cars cars = new Cars(List.of(car1, car2));
        cars.moveAll();
        final List<Car> winners = cars.getWinners(1);
        //then
        assertAll(
                () -> assertThat(winners).hasSize(1),
                () -> assertThat(winners).contains(car1)
        );
    }
}
