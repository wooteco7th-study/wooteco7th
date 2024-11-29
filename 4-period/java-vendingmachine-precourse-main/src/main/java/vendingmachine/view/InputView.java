package vendingmachine.view;

import static vendingmachine.exception.ErrorMessage.BLANK;
import static vendingmachine.exception.ErrorMessage.INVALID_HOLDING_AMOUNT;
import static vendingmachine.exception.ErrorMessage.INVALID_INPUT_AMOUNT;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import vendingmachine.util.InputValidator;
import vendingmachine.util.StringParser;

public class InputView {

    public int readHoldingAmount() {
        return StringParser.parseToInteger(readLine(), INVALID_HOLDING_AMOUNT);
    }

    public int readInputPrice() {
        return StringParser.parseToInteger(readLine(), INVALID_INPUT_AMOUNT);
    }

    public List<String> readHoldingProduct() {
        return StringParser.parseByDelimiter(readLine(), ";");
    }

    private String readLine() {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, BLANK);
        return line;
    }

    public String readOrderProduct() {
        return readLine();
    }
}
