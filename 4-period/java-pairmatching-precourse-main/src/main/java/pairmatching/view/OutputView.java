package pairmatching.view;

import pairmatching.domain.Pair;

import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_MSG = NEW_LINE + "페어 매칭 결과입니다.";

    public void printResult(final List<Pair> result) {
        System.out.println(RESULT_MSG);
        for (Pair pair : result) {
            System.out.println(String.join(" : ", pair.getPair()));
        }
    }

    public static void printError(String error) {
        System.out.println(error);
    }
}
