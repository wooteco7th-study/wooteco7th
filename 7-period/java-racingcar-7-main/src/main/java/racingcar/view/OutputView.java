package racingcar.view;

import java.util.List;
import racingcar.dto.RacingProgress;

public interface OutputView {

    void printAskCarNames();

    void printAskAttempt();

    void printRacingProgresses(final List<RacingProgress> racingProgresses);

    void printRacingWinners(final List<String> racingWinners);
}
