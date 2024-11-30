package bridge.view;

import bridge.domain.BridgeLogType;
import bridge.domain.GameResult;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INTRO = "다리 건너기 게임을 시작합니다.";
    private static final String ASK_BRIDGE_LENGTH = "다리의 길이를 입력해주세요.";
    private static final String ASK_MOVE_COMMAND = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String BRIDGE_LOG = "[ %s ]";
    private static final String BRIDGE_LOG_DELIMITER = " | ";
    private static final String ASK_RETRY = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String GAME_RESULT_HEADER = "최종 게임 결과";
    private static final String GAME_CLEAR = "게임 성공 여부: %s";
    private static final String GAME_ATTEMPT = "총 시도한 횟수: %d";


    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final List<BridgeLogType> upBridgeLog, final List<BridgeLogType> downBridgeLog) {
        printlnMessage(createBridgeLogMessage(upBridgeLog) + LINE_SEPARATOR + createBridgeLogMessage(downBridgeLog));
    }

    public void printIntro() {
        printlnMessage(INTRO);
    }

    public void printAskBridgeLength() {
        printlnMessage(LINE_SEPARATOR + ASK_BRIDGE_LENGTH);
    }

    public void printAskMoveCommand() {
        printlnMessage(LINE_SEPARATOR + ASK_MOVE_COMMAND);
    }

    public void printAskRetry() {
        printlnMessage(LINE_SEPARATOR + ASK_RETRY);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final List<BridgeLogType> upBridgeLog, final List<BridgeLogType> downBridgeLog) {
        printlnMessage(LINE_SEPARATOR + GAME_RESULT_HEADER);
        printMap(upBridgeLog, downBridgeLog);
    }

    public void printClearAndAttempt(final GameResult result, final int attempt) {
        final String message = String.format(GAME_CLEAR, result.getExpression());
        printMessage(LINE_SEPARATOR + message + LINE_SEPARATOR + String.format(GAME_ATTEMPT, attempt));
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final String message) {
        System.out.print(message);
    }

    private String createBridgeLogMessage(final List<BridgeLogType> bridgeLog) {
        final String message = bridgeLog.stream()
                .map(BridgeLogType::getExpression)
                .collect(Collectors.joining(BRIDGE_LOG_DELIMITER));
        return String.format(BRIDGE_LOG, message);
    }
}
