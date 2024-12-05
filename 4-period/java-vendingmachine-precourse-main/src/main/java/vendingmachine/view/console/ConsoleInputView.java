package vendingmachine.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import vendingmachine.error.ErrorMessage;
import vendingmachine.util.StringParser;
import vendingmachine.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_SEMI_COLON = ";";

    @Override
    public int readMoney() {
        final String input = readInput();
        return StringParser.parseToInt(input, ErrorMessage.INVALID_MONEY_FORMAT);
    }

    @Override
    public List<String> readProducts() {
        final String input = readInput();
        return StringParser.parseToTokens(input, DELIMITER_SEMI_COLON);
    }

    @Override
    public String purchaseProduct() {
        return readInput();
    }

    private String readInput() {
        return Console.readLine();
    }
}
