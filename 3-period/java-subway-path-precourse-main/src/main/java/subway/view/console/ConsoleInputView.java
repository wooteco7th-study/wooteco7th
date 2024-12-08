package subway.view.console;

import java.io.Console;
import java.util.Scanner;
import subway.view.InputView;

public class ConsoleInputView implements InputView {

    private final Scanner scanner;

    public ConsoleInputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readInput() {
        return scanner.nextLine();
    }
}
