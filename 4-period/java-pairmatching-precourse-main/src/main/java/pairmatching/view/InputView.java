package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

import static pairmatching.exception.ExceptionMessage.INPUT_BLANK;

public class InputView {

    private static final String CHOOSE_OPTION_MSG = """
            기능을 선택하세요.
            1. 페어 매칭
            2. 페어 조회
            3. 페어 초기화
            Q. 종료""";

    public String readOption() {
        return getValidatedInput(CHOOSE_OPTION_MSG);
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
