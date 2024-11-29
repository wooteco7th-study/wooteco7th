package baseball.view.console;

import baseball.constant.GameCommand;
import baseball.util.StringParser;
import baseball.view.InputView;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_BLANK = "";

    @Override
    public List<Integer> readNumbers() {
        final String input = readInput();
        return StringParser.parseToNumberTokens(input, DELIMITER_BLANK);
    }

    @Override
    public GameCommand readGameCommand() {
        final String input = readInput();
        return GameCommand.findByInput(input);
    }

    private String readInput() {
        return Console.readLine();
    }
}
