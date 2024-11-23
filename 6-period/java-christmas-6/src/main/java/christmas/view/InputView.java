package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

import static christmas.exception.ExceptionMessage.INPUT_BLANK;
import static christmas.exception.ExceptionMessage.INVALID_NUMBER;
import static christmas.exception.ExceptionMessage.INVALID_ORDER;

public class InputView {

    private static final String INPUT_VISIT_DATE_MSG = """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
            """;
    private static final String INPUT_MENU_AND_QUANTITY_MSG = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final Pattern ORDER_FORMAT = Pattern.compile("^([가-힣]+-\\d+)(,([가-힣]+-\\d+))*$");

    public int readVisitDate() {
        String input = getValidatedInput(INPUT_VISIT_DATE_MSG);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    public String readMenuAndQuantity() {
        String input = getValidatedInput(INPUT_MENU_AND_QUANTITY_MSG);
        validateFormat(input);
        return input;
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

    private void validateFormat(final String input) {
        if (isNotMatched(input)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private boolean isNotMatched(String input) {
        return !ORDER_FORMAT.matcher(input).find();
    }
}
