package baseball.view;

import baseball.util.StringParser;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static baseball.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {

    private static final String INPUT_NUMBERS_MSG = "숫자를 입력해주세요 : ";
    private static final String INPUT_RESTART_MSG = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";


    public List<Integer> readNumbers() {
        String input = getValidatedInput(INPUT_NUMBERS_MSG);
        return StringParser.parseToList(input);
    }

    public String readRestartOrNot() {
        return getValidatedInput(INPUT_RESTART_MSG);
    }

    private String getValidatedInput(String message) {
        System.out.print(message);
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
