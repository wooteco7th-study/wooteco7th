package racingcar.view.console;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RaceProgress;
import racingcar.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ASK_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ASK_ATTEMPT = "시도할 횟수는 몇 회인가요?";
    private static final String PROGRESSES_TITLE = "실행 결과";
    private static final String HYPHEN = "-";
    private static final String PROGRESS = "%s : %s";
    private static final String WINNERS = "최종 우승자 : %s";
    private static final String DELIMITER_COMMA = ", ";


    @Override
    public void printAskCarNames() {
        printlnMessage(ASK_CAR_NAMES);
    }

    @Override
    public void printAskAttempt() {
        printlnMessage(ASK_ATTEMPT);
    }

    @Override
    public void printRacingProgresses(final List<RaceProgress> raceProgresses) {
        final String message = raceProgresses.stream()
                .map(raceProgress -> String.format(PROGRESS, raceProgress.carName(),
                        HYPHEN.repeat(raceProgress.score())))
                .collect(Collectors.joining(LINE_SEPARATOR));

        printlnMessage(PROGRESSES_TITLE + LINE_SEPARATOR + message);
    }

    @Override
    public void printRacingWinners(final List<String> racingWinners) {
        final String message = String.format(WINNERS, String.join(DELIMITER_COMMA, racingWinners));
        printlnMessage(message);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }
}
