package racingcar.game;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.*;

public class GameController {
    private static final String MOVE_MARK = "-";
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // given
        List<String> carNames = inputView.requestCarNames();
        List<Car> cars = carNames.stream()
                .map(Car::of)
                .collect(Collectors.toList());

        int rounds = inputView.requestRoundCount();
        inputView.messageInitiateGameResult();
        CarMovePolicy carMovePolicy = new CarMovePolicy();

        List<RaceInfo> finalRaceInfos = null;

        // 게임 진행
        for(int i = 0; i < rounds; i++) {
            // 이전 상태를 유지하며 이동함
            cars = cars.stream()
                    .map(car -> car.move(carMovePolicy))
                    .collect(Collectors.toList());

            // 현재 라운드 정보 생성
            List<RaceInfo> raceInfos = cars.stream()
                    .map(Car::carInfo)
                    .toList();

            outputView.printRaceInfos(raceInfos);
            finalRaceInfos = raceInfos;
        }
        // then
        // 최종 승자 발표
        Winners winners = new GameResult(finalRaceInfos).pickWinners();
        outputView.announceWinners(winners);
    }

}
