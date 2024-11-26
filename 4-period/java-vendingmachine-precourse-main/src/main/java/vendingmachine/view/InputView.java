package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

import static vendingmachine.exception.ExceptionMessage.INPUT_BLANK;
import static vendingmachine.exception.ExceptionMessage.INVALID_AMOUNT;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String AMOUNT_HELD_MSG = "자판기가 보유하고 있는 금액을 입력해 주세요.";

    public int readAmountHeld() {
        String input = getValidatedInput(AMOUNT_HELD_MSG);
        return parseToValidNumber(input);
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

    private int parseToValidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT.getMessage());
        }
    }

}
