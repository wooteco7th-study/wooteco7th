package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.exception.ErrorMessage;
import pairmatching.util.InputValidator;

public class InputView {

    public String readFunction() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    public String readSelect() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    public String readRequestRetry() {
        return readLine(ErrorMessage.INVALID_COMMAND);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
