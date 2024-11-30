package bridge.view;

import bridge.dto.MoveResultDto;
import bridge.dto.TotalResultDto;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final List<MoveResultDto> resultDtos) {
        StringBuilder upText = makeBridgeText(resultDtos, 'U');
        showln(upText.toString());
        StringBuilder downText = makeBridgeText(resultDtos, 'D');
        showln(downText + LINE);
    }

    String bridgeStart = "[";
    String bridgeSquare = " %s ";
    String bridgeAppended = "|";
    String bridgeEnd = "]";
    String ok = "O";
    String notOk = "X";
    String blank = " ";

    private StringBuilder makeBridgeText(final List<MoveResultDto> resultDtos, final char direction) {
        StringBuilder totalText = new StringBuilder(bridgeStart);
        for (int i = 0; i < resultDtos.size(); i++) {
            MoveResultDto resultDto = resultDtos.get(i);
            totalText.append(makeEachSquare(direction, resultDto));
            if (i == resultDtos.size() - 1) {
                totalText.append(bridgeEnd);
                continue;
            }
            totalText.append(bridgeAppended);
        }
        return totalText;
    }

    private StringBuilder makeEachSquare(final char direction, final MoveResultDto resultDto) {
        StringBuilder text = new StringBuilder();
        boolean isRight = resultDto.isRightMove();
        String rightText = makeRightText(resultDto.direction(), direction, isRight);
        text.append(format(bridgeSquare, rightText));
        return text;
    }

    public String makeRightText(char direction, char compared, final boolean isRight) {
        if (direction == compared) {
            if (isRight) {
                return ok;
            }
            return notOk;
        }
        return blank;
    }

    // 사용자의 위치
    // O, X : 지금까지 사용자가 이동했던 위치
    // O, X : 다리가 맞는지 아닌지
    //     true false true
    // pos 1: 2: 3

    /*
    이동할 칸을 선택해주세요. (위: U, 아래: D)
    U
    [ O ]
    [   ]

    이동할 칸을 선택해주세요. (위: U, 아래: D)
    U
    [ O | X ]
    [   |   ]
     */

    private static final String LINE = System.lineSeparator();
    private static final String start = "다리 건너기 게임을 시작합니다.";
    private static final String startLength = "다리의 길이를 입력해주세요.";
    private static final String selectSquare = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String restart = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String finalResult = "최종 게임 결과";
    private static final String successResult = "게임 성공 여부: %s";
    private static final String success = "성공";
    private static final String fail = "실패";
    private static final String retrycount = "총 시도한 횟수: %d";

    public void startMessage() {
        showln(start);
        showln(LINE + startLength);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final TotalResultDto totalResultDto, final int tryCount) {
        printMap(totalResultDto.finalResult());
        showln(format(successResult, makeIsSuccess(totalResultDto.isSuccess())));
        showln(format(retrycount, tryCount));
    }

    private String makeIsSuccess(final boolean isSuccess) {
        if (isSuccess) {
            return success;
        }
        return fail;
    }

    public void showFinalResultMessage() {
        showln(finalResult);
    }

    public void restartMessage() {
        showln(restart);
    }

    public void selectDirection() {
        showln(selectSquare);
    }

    public void showException(Exception exception) {
        showln(exception.getMessage());
    }

    private String format(String format, Object... args) {
        return String.format(format, args);
    }

    private void showln(String message) {
        System.out.println(message);
    }
}
