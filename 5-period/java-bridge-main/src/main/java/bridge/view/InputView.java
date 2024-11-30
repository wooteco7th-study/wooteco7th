package bridge.view;

import bridge.util.StringParser;
import camp.nextstep.edu.missionutils.Console;

import static bridge.exception.ExceptionMessage.INPUT_BLANK;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String BRIDGE_SIZE_MSG = """
            다리 건너기 게임을 시작합니다.
            
            다리의 길이를 입력해주세요.""";
    private static final String MOVING_MSG = NEW_LINE + "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String GAME_COMMAND_MSG = NEW_LINE + "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";

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
        return getValidatedInput(MOVING_MSG);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return getValidatedInput(GAME_COMMAND_MSG);
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
