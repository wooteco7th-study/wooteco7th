package racingcar.game;

import java.util.List;
import java.util.stream.IntStream;
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
        List<Car> cars = initializeGame();
        int rounds = getRoundCount();
        showGameProgress(cars, rounds);
        announceResult(cars, rounds);
    }

    private List<Car> initializeGame() {
        List<String> carNames = inputView.requestCarNames();
        return carService.createCarsByNames(carNames);
    }

    private int getRoundCount() {
        return inputView.requestRoundCount();
    }

    private void showGameProgress(List<Car> cars, int rounds) {
        inputView.messageInitiateGameResult();
        IntStream.range(0, rounds)
                .forEach(i -> {
                    List<RaceInfo> raceInfos = gameService.play(cars, rounds);
                    outputView.printRaceInfos(raceInfos);
                });
    }

    private void announceResult(List<Car> cars, int rounds) {
        GameResult gameResult = new GameResult(gameService.play(cars, rounds));
        outputView.announceWinners(gameResult.pickWinners());
    }
}
