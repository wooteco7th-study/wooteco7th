package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Inventories;
import vendingmachine.util.StringParser;

import java.util.regex.Pattern;

import static vendingmachine.exception.ExceptionMessage.AMOUNT_MUST_BE_POSITIVE;
import static vendingmachine.exception.ExceptionMessage.INPUT_BLANK;
import static vendingmachine.exception.ExceptionMessage.INVALID_AMOUNT;
import static vendingmachine.exception.ExceptionMessage.INVALID_FORMAT;

public class InputView {

    private static final Pattern INPUT_FORMAT = Pattern.compile("^(\\[[가-힣]+,\\d+,\\d+\\])(;\\[[가-힣]+,\\d+,\\d+\\])*$");

    private static final String NEW_LINE = System.lineSeparator();
    private static final String AMOUNT_HELD_MSG = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INVENTORIES_MSG = NEW_LINE + "상품명과 가격, 수량을 입력해 주세요.";
    private static final String ORDER_AMOUNT_MSG = NEW_LINE + "투입 금액을 입력해 주세요.";
    private static final String LEFT_AMOUNT_MSG = NEW_LINE + "투입 금액: %d원";
    private static final String ORDER_PRODUCT_MSG = NEW_LINE + "구매할 상품명을 입력해 주세요.";

    public int readAmountHeld() {
        String input = getValidatedInput(AMOUNT_HELD_MSG);
        return parseToValidNumber(input);
    }

    public Inventories readInventories() {
        String input = getValidatedInput(INVENTORIES_MSG);
        validateInputFormat(input);
        return StringParser.parseInventories(input);
    }

    public int readOrderAmount() {
        String input = getValidatedInput(ORDER_AMOUNT_MSG);
        return parseToValidNumber(input);
    }

    public String readOrderProductName(final int leftAmount) {
        System.out.printf(LEFT_AMOUNT_MSG, leftAmount);
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

    private int parseToValidNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            validatePositiveNumber(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_AMOUNT.getMessage());
        }
    }

    private static void validatePositiveNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_POSITIVE.getMessage());
        }
    }

    private void validateInputFormat(String input) {
        if (!INPUT_FORMAT.matcher(input).find()) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
