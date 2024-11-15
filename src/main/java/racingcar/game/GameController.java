package racingcar.game;

import java.util.List;
import racingcar.car.Car;
import racingcar.car.GameResult;
import racingcar.car.RaceInfo;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CarService carService;
    private final GameService gameService;

    public GameController(InputView inputView, OutputView outputView,
                          CarService carService, GameService gameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.carService = carService;
        this.gameService = gameService;
    }

    public void run() {
        List<String> carNames = inputView.requestCarNames();
        List<Car> cars = carService.createCarsByNames(carNames);
        int rounds = inputView.requestRoundCount();

        inputView.messageInitiateGameResult();

        List<Car> currentRoundCars = cars;
        for (int i = 0; i < rounds; i++) {
            List<RaceInfo> raceInfos = gameService.play(cars, rounds);
            outputView.printRaceInfos(raceInfos);
        }

        GameResult gameResult = new GameResult(gameService.play(cars, rounds));
        outputView.announceWinners(gameResult.pickWinners());
    }
}
