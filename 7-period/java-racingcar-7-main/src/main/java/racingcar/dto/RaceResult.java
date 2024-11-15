package racingcar.dto;

import java.util.List;

public record RaceResult(
        List<RaceProgress> raceProgresses
) {
}
