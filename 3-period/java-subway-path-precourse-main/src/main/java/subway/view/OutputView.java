package subway.view;

import java.util.List;

public interface OutputView {

    void printMainCommand();

    void printAskCommand();

    void printPathCommand();

    void printAskStartStation();

    void printAskEndStation();

    void printResult(final List<String> shortestPath, final int distance, final int time);
}
