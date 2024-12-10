package store.product.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.error.ErrorMessage;
import store.util.StringParser;
import store.util.StringValidator;

public class InputView {

    private static final String DELIMITER_COMMA = ",";
    private static final String WRAPPER = "[\\[\\]]";

    public List<String> readProducts() {
        final List<String> tokens = StringParser.parseToTokens(readInput().strip(), DELIMITER_COMMA);
        validateFormat(tokens);
        return tokens.stream()
                .map(token -> StringParser.removePattern(token, WRAPPER))
                .toList();
    }

    private void validateFormat(final List<String> tokens) {
        for (String token : tokens) {
            StringValidator.validateFormat(token, ErrorMessage.INVALID_INPUT);
        }
    }

    private String readInput() {
        return Console.readLine();
    }
}
