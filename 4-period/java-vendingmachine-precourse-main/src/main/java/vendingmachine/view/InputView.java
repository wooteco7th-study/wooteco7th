package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.util.StringParser;

import java.util.regex.Pattern;

import static vendingmachine.exception.ExceptionMessage.INPUT_BLANK;
import static vendingmachine.exception.ExceptionMessage.INVALID_FORMAT;

public class InputView {

    private static final Pattern INPUT_FORMAT = Pattern.compile("^(\\[[가-힣]+,\\d+,\\d+\\])(;\\[[가-힣]+,\\d+,\\d+\\])*$");

    private static final String NEW_LINE = System.lineSeparator();
    private static final String AMOUNT_HELD_MSG = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INVENTORIES_MSG = NEW_LINE + "상품명과 가격, 수량을 입력해 주세요.";
    private static final String ORDER_AMOUNT_MSG = NEW_LINE + "투입 금액을 입력해 주세요.";
    private static final String ORDER_PRODUCT_MSG = NEW_LINE + "구매할 상품명을 입력해 주세요.";

    public int readAmountHeld() {
        String input = getValidatedInput(AMOUNT_HELD_MSG);
        return StringParser.parseToValidNumber(input);
    }

    public String readInventories() {
        String input = getValidatedInput(INVENTORIES_MSG);
        validateInputFormat(input);
        return input;
    }

    public int readOrderAmount() {
        String input = getValidatedInput(ORDER_AMOUNT_MSG);
        return StringParser.parseToValidNumber(input);
    }

    public String readOrderProductName() {
        return getValidatedInput(ORDER_PRODUCT_MSG);
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

    private void validateInputFormat(String input) {
        if (!INPUT_FORMAT.matcher(input).find()) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
