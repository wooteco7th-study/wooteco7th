package racingcar.game;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.Car;

public class CarService {

    public List<Car> createCarsByNames(List<String> carNames){
         return carNames.stream()
                .map(Car::of)
                .collect(Collectors.toList());

    }

}
