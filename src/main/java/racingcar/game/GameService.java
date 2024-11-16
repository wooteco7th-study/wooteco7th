package racingcar.game;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.Car;
import racingcar.car.CarMovePolicy;
import racingcar.car.RaceInfo;

public class GameService {
    private final CarMovePolicy carMovePolicy;

    public GameService(CarMovePolicy carMovePolicy) {
        this.carMovePolicy = carMovePolicy;
    }

    public List<RaceInfo> play(List<Car> cars, int rounds) {

        List<Car> currentRoundCars = cars;

        for (int i = 0; i < rounds; i++) {
            currentRoundCars = moveCars(currentRoundCars);
        }

        return createRaceInfos(currentRoundCars);
    }

    private List<Car> moveCars(List<Car> cars) {
        return cars.stream()
                .map(car -> car.move(carMovePolicy))
                .collect(Collectors.toList());
    }

    private List<RaceInfo> createRaceInfos(List<Car> cars) {
        return cars.stream()
                .map(Car::carInfo)
                .toList();
    }
}
