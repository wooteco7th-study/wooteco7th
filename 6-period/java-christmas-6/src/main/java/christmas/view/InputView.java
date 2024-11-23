package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.exception.ExceptionMessage.INPUT_BLANK;
import static christmas.exception.ExceptionMessage.INVALID_NUMBER;

public class InputView {

    private static final String INPUT_VISIT_DATE_MSG = """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
            """;

    public int readVisitDate() {
        String input = getValidatedInput(INPUT_VISIT_DATE_MSG);
        validateNumber(input);
        return Integer.parseInt(input);
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

    private void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }
}
