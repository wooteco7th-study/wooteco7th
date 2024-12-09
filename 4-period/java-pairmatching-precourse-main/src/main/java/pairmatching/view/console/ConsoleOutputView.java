package pairmatching.view.console;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.dto.PairName;
import pairmatching.view.OutputView;

public class ConsoleOutputView implements OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PAIR_COMMAND =
            "기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료";
    private static final String PREVIEW =
            "#############################################\n"
                    + "과정: 백엔드 | 프론트엔드\n"
                    + "미션:\n"
                    + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                    + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                    + "  - 레벨3: \n"
                    + "  - 레벨4: 성능개선 | 배포\n"
                    + "  - 레벨5: \n"
                    + "############################################";
    private static final String ASK_MISSION = "과정, 레벨, 미션을 선택하세요."
            + LINE_SEPARATOR + "ex) 백엔드, 레벨1, 자동차경주";
    private static final String PAIR_DELIMITER = " : ";
    private static final String MATCH_RESULT_HEADER = "페어 매칭 결과입니다.";
    private static final String ASK_REMATCH = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?" + LINE_SEPARATOR + "네 | 아니오";
    private static final String CLEAR = "초기화 되었습니다.";

    @Override
    public void printPairCommand() {
        printlnMessage(LINE_SEPARATOR + PAIR_COMMAND);
    }

    @Override
    public void printAskMission() {
        printlnMessage(ASK_MISSION);
    }

    @Override
    public void printMatchResult(final List<PairName> pairNames) {
        final String message = pairNames.stream()
                .map(pairName -> String.join(PAIR_DELIMITER, pairName.crews()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(LINE_SEPARATOR + MATCH_RESULT_HEADER + LINE_SEPARATOR + message);
    }

    @Override
    public void printAskRematch() {
        printlnMessage(ASK_REMATCH);
    }

    @Override
    public void printClear() {
        printlnMessage(LINE_SEPARATOR + CLEAR);
    }

    @Override
    public void printPreview() {
        printlnMessage(LINE_SEPARATOR + PREVIEW);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }
}
