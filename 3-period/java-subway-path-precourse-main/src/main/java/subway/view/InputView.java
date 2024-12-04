package subway.view;

import static subway.exception.ErrorMessage.INVALID_COMMAND;

import java.util.Scanner;
import subway.exception.CustomIllegalArgumentException;
import subway.exception.ErrorMessage;
import subway.util.InputValidator;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    private String readLine(ErrorMessage errorMessage) {
        String line = scanner.nextLine();
        InputValidator.validateNotNullOrBlank(line, errorMessage);
        return line;
    }

    public String informFunction() {
        return readLine(INVALID_COMMAND);
    }
}
