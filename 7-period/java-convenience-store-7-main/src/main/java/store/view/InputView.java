package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.exception.ErrorMessage;
import store.util.InputValidator;
import store.util.StringParser;

public class InputView {
    // ^\[([가-힣a-zA-z]+)-([1-9]\d*)\]$

    public List<String> readOrder() {
        String line = readLine(ErrorMessage.INVALID_ORDER_FORMAT);
        return StringParser.parseByDelimiter(line, ",");
    }

    public String readRequestMembership() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    public String readRequestRetry() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    public String readRequestRegularPrice() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    public String readRequestBonus() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
