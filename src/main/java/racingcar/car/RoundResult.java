package racingcar.car;

import java.util.List;

public class RoundResult {
    private final List<RaceInfo> raceInfos;

    public RoundResult(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }

    public Winners pickWinners() {
        int maxMove = raceInfos.stream()
                .mapToInt(info -> info.getTotalMoveAmount())
                .max()
                .orElse(0);

        List<String> winners = raceInfos.stream()
                .filter(info -> info.getTotalMoveAmount() == maxMove)
                .map(i->i.getName())
                .toList();
        return new Winners(winners);
    }

}
