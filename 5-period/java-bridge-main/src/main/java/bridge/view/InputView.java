package bridge.view;

import bridge.util.StringParser;
import camp.nextstep.edu.missionutils.Console;

import static bridge.exception.ExceptionMessage.INPUT_BLANK;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String BRIDGE_SIZE_MSG = """
            다리 건너기 게임을 시작합니다.
            
            다리의 길이를 입력해주세요.
            """;

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        String input = getValidatedInput(BRIDGE_SIZE_MSG);
        return StringParser.parseToValidNumber(input);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return null;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return null;
    }

    private String getValidatedInput(String message) {
        System.out.println(message);
        String input = Console.readLine().strip();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK.getMessage());
        }
    }
}
