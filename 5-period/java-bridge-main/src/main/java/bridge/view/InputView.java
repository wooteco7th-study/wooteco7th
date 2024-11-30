package bridge.view;

import static bridge.exception.ErrorMessage.INVALID_BRIDGE_LENGTH;
import static bridge.exception.ErrorMessage.INVALID_DIRECTION;

import bridge.exception.ErrorMessage;
import bridge.util.InputValidator;
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
        return StringParser.parseToInteger(readLine(INVALID_BRIDGE_LENGTH), INVALID_BRIDGE_LENGTH);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return readLine(INVALID_DIRECTION);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }


    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
