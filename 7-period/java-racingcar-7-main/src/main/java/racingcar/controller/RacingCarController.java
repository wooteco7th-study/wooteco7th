package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Attempt;
import racingcar.domain.Car;
import racingcar.domain.CarFactory;
import racingcar.domain.Race;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.RaceProgress;
import racingcar.dto.RaceResult;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingCarController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final List<Car> cars = requestCars();
        final Attempt attempt = requestAttempt();
        startRace(cars, attempt);
    }

    private void startRace(final List<Car> cars, final Attempt attempt) {
        final Race race = new Race(cars);
        final List<RaceResult> raceResults = generateRaceResults(race, attempt);
        outputView.printRaceResults(raceResults);
        outputView.printRaceWinners(race.getWinners());
    }

    private List<RaceResult> generateRaceResults(final Race race, final Attempt attempt) {
        final List<RaceResult> raceResults = new ArrayList<>();
        for (int turn = 0; turn < attempt.getValue(); turn++) {
            final List<Car> cars = race.move();
            final List<RaceProgress> raceProgresses = createRaceProgresses(cars);
            raceResults.add(new RaceResult(raceProgresses));
        }
        return raceResults;
    }

    private List<RaceProgress> createRaceProgresses(final List<Car> cars) {
        return cars.stream()
                .map(RaceProgress::of)
                .toList();
    }

    private List<Car> requestCars() {
        outputView.printAskCarNames();
        final List<String> carNames = inputView.readCarNames();
        final CarFactory carFactory = new CarFactory(new RandomNumberGenerator(), carNames);
        return carFactory.generateCars();
    }

    private Attempt requestAttempt() {
        outputView.printAskAttempt();
        final int attempt = inputView.readAttempt();
        return new Attempt(attempt);
    }

}
