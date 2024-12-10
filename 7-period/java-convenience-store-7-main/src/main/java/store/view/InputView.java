package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.exception.ErrorMessage;
import store.util.InputValidator;
import store.util.StringParser;

public class InputView {

    private static final String DELIMITER = ",";

    public List<String> readOrder() {
        String line = readLine(ErrorMessage.INVALID_INPUT);
        return StringParser.parseByDelimiter(line, DELIMITER);
    }

    public String readAnswer() {
        return readLine(ErrorMessage.INVALID_INPUT);
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = Console.readLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }
}
