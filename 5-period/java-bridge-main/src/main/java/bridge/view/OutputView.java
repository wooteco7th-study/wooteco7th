package bridge.view;

import bridge.dto.ResultDto;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String START = "다리 건너기 게임을 시작합니다.";
    private static final String START_LENGTH = "다리의 길이를 입력해주세요.";
    private static final String SELECT_SQUARE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String RESTART = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String FINAL_RESULT = "최종 게임 결과";
    private static final String SUCCESS_RESULT = "게임 성공 여부: %s";
    private static final String SUCCESS = "성공";
    private static final String FAIL = "실패";
    private static final String RETRYCOUNT = "총 시도한 횟수: %d";
    private static final String DELIMITER = " | ";
    private static final String PREFIX = "[ ";
    private static final String SUFFIX = " ]";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final List<ResultDto> results) {
        String upText = results.stream()
                .map(ResultDto::up)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));

        String downText = results.stream()
                .map(ResultDto::down)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
        showln(upText + LINE + downText);
    }

    public void startMessage() {
        showln(START);
        showln(LINE + START_LENGTH);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final List<ResultDto> results, final int tryCount) {
        showln(LINE + FINAL_RESULT);
        printMap(results);
        showAnalysis(results, tryCount);
    }

    public void restartMessage() {
        showln(LINE + RESTART);
    }

    public void selectDirection() {
        showln(LINE + SELECT_SQUARE);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private void showAnalysis(final List<ResultDto> results, final int tryCount) {
        showln(LINE + format(SUCCESS_RESULT, getLastResult(results.getLast())));
        showln(format(RETRYCOUNT, tryCount));
    }

    private String getLastResult(final ResultDto last) {
        if (last.isSuccess()) {
            return SUCCESS;
        }
        return FAIL;
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
