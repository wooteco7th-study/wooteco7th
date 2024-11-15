package racingcar.game;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.*;

public class GameController {
    private static final String MOVE_MARK = "-";

    public static void main(String[] args) {
        new GameController().run();
    }
    public void run() {
        // given
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<String> carNames = Arrays.stream(readLine().trim().split(",")).toList();

        System.out.println("시도할 횟수는 몇 회 인가요?");
        int rounds = Integer.parseInt(readLine().trim());


        CarMovePolicy carMovePolicy = new CarMovePolicy();

        // when
        // 자동차 초기 세팅
        List<Car> cars = carNames.stream()
                .map(Car::of)
                .collect(Collectors.toList());

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

            printRaceInfos(raceInfos);
            finalRaceInfos = raceInfos;
        }
        // then
        // 최종 승자 발표
        Winners winners = new GameResult(finalRaceInfos).pickWinners();
        announceWinners(winners);
    }

    private void announceWinners(Winners winners) {
        System.out.println("최종 우승자" + " : " + winners.formatWinners());
    }

    private void printRaceInfos(List<RaceInfo> raceInfos) {

            raceInfos.stream()
                    .forEach(raceInfo -> {
                        String moveMarks = MOVE_MARK.repeat(raceInfo.getTotalMoveAmount());
                        System.out.println(raceInfo.getName() + " : " + moveMarks);
                    });
            System.out.println();
    }

}
