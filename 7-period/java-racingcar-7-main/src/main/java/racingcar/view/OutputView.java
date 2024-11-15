package racingcar.view;

import java.util.List;
import racingcar.dto.RaceProgress;
import racingcar.dto.RaceResult;

public interface OutputView {

    void printAskCarNames();

    void printAskAttempt();

    void printRaceResults(final List<RaceResult> raceResults);

    void printRaceWinners(final List<String> racingWinners);
}
