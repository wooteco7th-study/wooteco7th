package vendingmachine.view;

import static vendingmachine.exception.ErrorMessage.BLANK;
import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_AMOUNT;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.util.Converter;
import vendingmachine.util.InputValidator;

public class InputView {

    public long readHoldingAmount() {
        return Converter.convertToLong(readLine(), INVALID_HOLDING_AMOUNT);
    }

    private String readLine() {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, BLANK);
        return line;
    }
}
