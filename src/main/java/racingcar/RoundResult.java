package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class RoundResult {
    private final List<RaceInfo> raceInfos;

    public RoundResult(List<RaceInfo> raceInfos) {
        this.raceInfos = raceInfos;
    }

}
