package pairmatching.view;

import pairmatching.domain.Pair;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_MSG = NEW_LINE + "페어 매칭 결과입니다.";
    private static final String CLEAR_MSG = NEW_LINE + "초기화 되었습니다.";

    private static final String RESULT_DELIMITER = " : ";

    public void printResult(final List<Pair> result) {
        System.out.println(RESULT_MSG);
        for (Pair pair : result) {
            System.out.println(String.join(RESULT_DELIMITER, pair.getNames()));
        }
    }

    public void printClearMsg() {
        System.out.println(CLEAR_MSG);
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
