package baseball.view;

import baseball.util.StringParser;
import camp.nextstep.edu.missionutils.Console;

import static baseball.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {

    private static final String INPUT_NUMBERS_MSG = "숫자를 입력해주세요 : ";

    public int readNumbers() {
        String input = getValidatedInput(INPUT_NUMBERS_MSG);
        return StringParser.parseToInt(input);
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
