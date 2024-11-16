package racingcar.game;

import java.util.List;
import racingcar.car.RaceInfo;
import racingcar.car.Winners;

public class OutputView {
    private static final String MOVE_MARK = "-";

    public void announceWinners(Winners winners) {
        System.out.println("최종 우승자" + " : " + winners.formatWinners());
    }
    public void printRaceInfos(List<RaceInfo> raceInfos) {

        raceInfos.stream()
                .forEach(raceInfo -> {
                    String moveMarks = MOVE_MARK.repeat(raceInfo.getTotalMoveAmount());
                    System.out.println(raceInfo.getName() + " : " + moveMarks);
                });
        System.out.println();
    }
}
