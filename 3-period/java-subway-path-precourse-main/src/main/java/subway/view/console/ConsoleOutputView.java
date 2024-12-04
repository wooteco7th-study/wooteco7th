package subway.view.console;

import java.util.List;
import java.util.stream.Collectors;
import subway.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String MAIN_COMMAND = "## 메인 화면" + LINE_SEPARATOR + "1. 경로 조회" + LINE_SEPARATOR + "Q. 종료";
    private static final String ASK_COMMAND = "## 원하는 기능을 선택하세요.";
    private static final String PATH_COMMAND =
            "## 경로 기준" + LINE_SEPARATOR + "1. 최단 거리" + LINE_SEPARATOR + "2. 최소 시간" + LINE_SEPARATOR
                    + "B. 돌아가기";
    private static final String ASK_START_STATION = "## 출발역을 입력하세요.";
    private static final String ASK_END_STATION = "## 도착역을 입력하세요.";
    private static final String RESULT_PREFIX = "[INFO] ";
    private static final String RESULT_HEADER = "## 조회 결과";
    private static final String RESULT_LINE = "---";
    private static final String DISTANCE = "총 거리: %dkm";
    private static final String TIME = "총 소요 시간: %d분";

    @Override
    public void printMainCommand() {
        printlnMessage(LINE_SEPARATOR + MAIN_COMMAND);
    }

    @Override
    public void printAskCommand() {
        printlnMessage(LINE_SEPARATOR + ASK_COMMAND);
    }

    @Override
    public void printPathCommand() {
        printlnMessage(LINE_SEPARATOR + PATH_COMMAND);
    }

    @Override
    public void printAskStartStation() {
        printlnMessage(LINE_SEPARATOR + ASK_START_STATION);
    }

    @Override
    public void printAskEndStation() {
        printlnMessage(LINE_SEPARATOR + ASK_END_STATION);
    }

    @Override
    public void printResult(final List<String> shortestPath, final int distance, final int time) {
        final StringBuilder sb = new StringBuilder();
        final String pathMessage = shortestPath.stream()
                .map(path -> RESULT_PREFIX + path)
                .collect(Collectors.joining(LINE_SEPARATOR));
        sb.append(RESULT_PREFIX + RESULT_LINE)
                .append(LINE_SEPARATOR + RESULT_PREFIX + String.format(DISTANCE, distance))
                .append(LINE_SEPARATOR + RESULT_PREFIX + String.format(TIME, time))
                .append(LINE_SEPARATOR + RESULT_PREFIX + RESULT_LINE)
                .append(LINE_SEPARATOR)
                .append(pathMessage);
        printlnMessage(LINE_SEPARATOR + RESULT_HEADER + LINE_SEPARATOR + sb);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
