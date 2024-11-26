package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.request.InventoriesRequest;
import vendingmachine.util.StringParser;

import java.util.regex.Pattern;

import static vendingmachine.exception.ExceptionMessage.INPUT_BLANK;
import static vendingmachine.exception.ExceptionMessage.INVALID_AMOUNT;
import static vendingmachine.exception.ExceptionMessage.INVALID_FORMAT;

public class InputView {

    private static final Pattern INPUT_FORMAT = Pattern.compile("^(\\[[가-힣]+,\\d+,\\d+\\])(;\\[[가-힣]+,\\d+,\\d+\\])*$");

    private static final String NEW_LINE = System.lineSeparator();
    private static final String AMOUNT_HELD_MSG = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String ORDER_MSG = "상품명과 가격, 수량을 입력해 주세요.";

    public int readAmountHeld() {
        String input = getValidatedInput(AMOUNT_HELD_MSG);
        return parseToValidNumber(input);
    }

    public InventoriesRequest readInventories() {
        String input = getValidatedInput(ORDER_MSG);
        validateInputFormat(input);
        return StringParser.parseInventories(input);
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

    private void validateInputFormat(String input) {
        if (!INPUT_FORMAT.matcher(input).find()) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
