package bridge.view;

import bridge.error.ErrorMessage;
import bridge.util.StringParser;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        final String input = readInput();
        return StringParser.parseToInt(input, ErrorMessage.INVALID_BRIDGE_LENGTH_FORMAT);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return readInput();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return readInput();
    }

    private String readInput() {
        return Console.readLine();
    }
}
